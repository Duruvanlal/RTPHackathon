package com.swapstech.hackathon.employer.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swapstech.hackathon.employer.model.Address;
//import com.swapstech.hackathon.employer.model.Address;
//import com.swapstech.hackathon.employer.model.Company;
import com.swapstech.hackathon.employer.model.Contractor;
import com.swapstech.hackathon.employer.model.EntityMaster;
import com.swapstech.hackathon.employer.model.InvoiceCustomer;
import com.swapstech.hackathon.employer.model.User;
import com.swapstech.hackathon.employer.model.UserActUpaMapping;
import com.swapstech.hackathon.employer.model.UserPaymentAccount;
import com.swapstech.hackathon.employer.model.UserType;
import com.swapstech.hackathon.employer.model.UserUpaMaster;
import com.swapstech.hackathon.employer.repository.AddressRepository;
import com.swapstech.hackathon.employer.repository.EntityRepository;
import com.swapstech.hackathon.employer.repository.InvoiceCustomerRepository;
import com.swapstech.hackathon.employer.repository.UserActUpaMapRepository;
import com.swapstech.hackathon.employer.repository.UserPmtActRepository;
//import com.swapstech.hackathon.employer.repository.AddressRepository;
//import com.swapstech.hackathon.employer.repository.CompanyRepository;
import com.swapstech.hackathon.employer.repository.UserRepository;
import com.swapstech.hackathon.employer.repository.UserUpaMastRepository;
import com.swapstech.hackathon.employer.util.VOMapper;
import com.swapstech.hackathon.employer.vo.LoginUserRequest;
import com.swapstech.hackathon.employer.vo.UserClient;


@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	EntityRepository entityRepository;
	
	@Autowired
	UserUpaMastRepository userUpaMastRepository;
	
	@Autowired
	UserActUpaMapRepository userActUpaMapRepository;
	
	@Autowired
	UserPmtActRepository userPmtActRepository;
	
	@Autowired
	InvoiceCustomerRepository invoiceCustomerRepository;

	@PostMapping(value = "login")
	public ResponseEntity<Object> login(@RequestBody LoginUserRequest user) {
		User principal = new User();
		try {
			principal = userRepository.findOneByUserNameAndPassword(user.getUserName(),
					user.getPassword());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(principal);
	}

	@PostMapping(value = "register")
	@Transactional(rollbackFor={Exception.class})
	public ResponseEntity<Void> registerEmployeeUser(@RequestBody UserClient userClient) {
		User user = VOMapper.convertToUserModel(userClient);
		try {
			user.getAddress().setAddressId(UUID.randomUUID().toString());
			user.setUserId(user.getUserName()); // UUID.randomUUID().toString()
			user.getEntity().setEntityId(UUID.randomUUID().toString());
//			if(user.getSameAddressAsEntity()) {
//				user.getEntity().getAddress().setAddressId(user.getAddress().getAddressId());
//			}else {
				user.getEntity().getAddress().setAddressId(UUID.randomUUID().toString());
			//}
			User createdUser = userRepository.save(user);
		
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	// @GetMapping(value = "user/{company-id}")
	// public ResponseEntity<List<User>> findAll(
	// @PathVariable(name="company-id") Long companyId
	// ) {
	// List<User> users = new ArrayList<User>();
	// try {
	// Company company = companyRepository.findOne(companyId);
	// users = userRepository.findAllUsersByCompanyId(companyId);
	// }catch(Exception ex) {
	// System.out.println(ex.getMessage()); //company
	// }
	// return ResponseEntity.ok(users);
	// }
	//

	@GetMapping(value = "users")
	public List<User> findAllUsers() {
		List<User> users = new ArrayList<User>();
		try {
			users = userRepository.findAll();
		} catch (Exception ex) {
			System.out.println(ex.getMessage()); // company
			return null; //new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return users;
	}

	
	@PostMapping(value = "user-accounts")
	public ResponseEntity<Object> saveAccounts(@RequestBody List<UserPaymentAccount> userPmtAct) {
		try {
			
			if(userPmtAct.size() > 0) {
				for(UserPaymentAccount acct :userPmtAct) {
					UserPaymentAccount isExists = userPmtActRepository.findOneByUserIdAndAccountType(acct.getUserId(), acct.getAccountType());
					if(!(isExists!=null)) {
						acct.setUserPmtActId(acct.getUserId()+"_"+acct.getAccountNumber());
						acct = userPmtActRepository.save(acct);
					}
				}
			}else {
				return (ResponseEntity<Object>) ResponseEntity.badRequest();
			}
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(userPmtAct);
	}
	
	@PutMapping(value = "user-accounts")
	@Transactional(rollbackFor={Exception.class})
	public ResponseEntity<Object> updateAccounts(@RequestBody UserPaymentAccount userPmtAct) {
		try {
			if(userPmtAct !=null) {
				userPmtAct = userPmtActRepository.save(userPmtAct);
								
				UserActUpaMapping userActUpaMapping = new UserActUpaMapping();
				userActUpaMapping.setUserPymtAcctId(userPmtAct.getUserPmtActId());
				userActUpaMapping.setUserUPAMstrId(userPmtAct.getUserId()+"_"+userPmtAct.getUpaCD());
				userActUpaMapping = userActUpaMapRepository.save(userActUpaMapping);
			}else {
				return (ResponseEntity<Object>) ResponseEntity.badRequest();
			}
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(userPmtAct);
	}
	
	
	@GetMapping(value = "user-accounts/{userId}")
	public ResponseEntity<List<UserPaymentAccount>> getUserAccounts(@PathVariable ("userId") String userId) {
	
		List<UserPaymentAccount> accounts = new ArrayList<UserPaymentAccount>();
		try {	
			accounts = userPmtActRepository.findByUserId(userId);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(accounts);
	}
	
	@GetMapping(value = "receiver-user-accounts/{userId}")
	public ResponseEntity<List<UserPaymentAccount>> getReceiverUserAccounts(@PathVariable ("userId") String userId) {
	
		List<UserPaymentAccount> accounts = new ArrayList<UserPaymentAccount>();
		try {	
			accounts = userPmtActRepository.findByUserIdNotIn(userId);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(accounts);
	}
	
	@GetMapping(value = "upa-id/{userId}")
	public ResponseEntity<List<UserUpaMaster>> getUserUpaMaster(@PathVariable ("userId") String userId) {
	
		List<UserUpaMaster> accounts = new ArrayList<UserUpaMaster>();
		try {	
			accounts = userUpaMastRepository.findByUserId(userId);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(accounts);
	}
	
	@PostMapping(value = "user/upa-id")
	public ResponseEntity<UserUpaMaster> saveUpa(@RequestBody UserUpaMaster userUpaMaster) {

		try {	
			userUpaMaster = userUpaMastRepository.save(userUpaMaster);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(userUpaMaster);
	}
	
	@GetMapping(value = "invoice-customers/{userId}")
	public ResponseEntity<List<InvoiceCustomer>> getInvoiceCustomers(@PathVariable ("userId") String userId) {
	
		List<InvoiceCustomer> invoiceCustomers = new ArrayList<InvoiceCustomer>();
		
		try {	
			invoiceCustomers = invoiceCustomerRepository.findByUserId(userId);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return ResponseEntity.ok(invoiceCustomers);
	}

}
