package com.swapstech.hackathon.employer.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import com.swapstech.hackathon.employer.model.Address;
//import com.swapstech.hackathon.employer.model.Company;
import com.swapstech.hackathon.employer.model.Contractor;
import com.swapstech.hackathon.employer.model.User;
import com.swapstech.hackathon.employer.model.UserPaymentAccount;
import com.swapstech.hackathon.employer.model.UserType;
import com.swapstech.hackathon.employer.repository.UserPmtActRepository;
//import com.swapstech.hackathon.employer.repository.AddressRepository;
//import com.swapstech.hackathon.employer.repository.CompanyRepository;
import com.swapstech.hackathon.employer.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserPmtActRepository userPmtActRepository;

	@PostMapping(value = "login")
	public ResponseEntity<Object> login(@RequestBody User user) {
		User principal = new User();
		try {
			principal = userRepository.findOneByUserNameAndPassword(user.getUserName(),
					user.getPassword());
		} catch (Exception ex) {
			return ResponseEntity.ok(ex.getMessage());
		}
		return ResponseEntity.ok(principal);
	}

	@PostMapping(value = "register")
	public ResponseEntity<Void> registerEmployeeUser(@RequestBody User user) {
		try {
			user.setType(UserType.Employee);
			user.setUserId(UUID.randomUUID().toString());
			userRepository.save(user);
		} catch (Exception e) {

			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<Void>(HttpStatus.OK);
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
	public ResponseEntity<List<User>> findAllUsers() {
		List<User> users = new ArrayList<User>();
		try {
			users = userRepository.findAll();
		} catch (Exception ex) {
			System.out.println(ex.getMessage()); // company
		}
		return ResponseEntity.ok(users);
	}

	
	@PostMapping(value = "user-accounts")
	public ResponseEntity<Object> saveAccounts(@RequestBody List<UserPaymentAccount> userPmtAct) {
	//	List<UserPaymentAccount> accounts = new ArrayList<UserPaymentAccount>();
		try {
			
			if(userPmtAct.size() > 0) {
				for(UserPaymentAccount acct :userPmtAct) {
					UserPaymentAccount isExists = userPmtActRepository.findOneByUserIdAndAccountType(acct.getUserId(), acct.getAccountType());
					if(!(isExists!=null)) {
						acct.setUpaCD("#"+acct.getUserId()+"_"+acct.getAccountType());
						userPmtActRepository.save(acct);
					}
				}
			}else {
				return (ResponseEntity<Object>) ResponseEntity.badRequest();
			}
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage()); // company
		}
		return ResponseEntity.ok("Success");
	}
	
	
	@GetMapping(value = "user-accounts")
	public ResponseEntity<List<UserPaymentAccount>> getUserAccounts() {
	
		List<UserPaymentAccount> accounts = new ArrayList<UserPaymentAccount>();
		try {	
			accounts = userPmtActRepository.findAll();
		} catch (Exception ex) {
			System.out.println(ex.getMessage()); // company
		}
		return ResponseEntity.ok(accounts);
	}

}
