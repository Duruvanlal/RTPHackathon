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

import com.swapstech.hackathon.employer.model.Address;
import com.swapstech.hackathon.employer.model.Company;
import com.swapstech.hackathon.employer.model.Contractor;
import com.swapstech.hackathon.employer.model.User;
import com.swapstech.hackathon.employer.model.UserType;
import com.swapstech.hackathon.employer.repository.CompanyRepository;
import com.swapstech.hackathon.employer.repository.UserRepository;

@CrossOrigin
@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	CompanyRepository companyRepository;
	
	
	@PostMapping(value = "login")
	public ResponseEntity<Object> login(
			@RequestBody User user) {
		User principal = new User();
		try {
			List<User> userList =  userRepository.findAllUserByUserNameAndPassword(user.getUserName(), user.getPassword());
			 if(userList.size()>0) {
				 principal.setId(userList.get(0).getId());
				 principal.setFirstName(userList.get(0).getFirstName());
				 principal.setLastName(userList.get(0).getLastName());
				 principal.setEmail(userList.get(0).getEmail());
				 principal.setPhone(userList.get(0).getPhone());
				 principal.setUserId(userList.get(0).getUserId());
				 principal.setUserName(userList.get(0).getUserName());
				 Company company = new Company();
				 if(userList.get(0).getCompany() != null) {
					 company.setId(userList.get(0).getCompany().getId());
					 company.setCompanyId(userList.get(0).getCompany().getCompanyId());
					 company.setName(userList.get(0).getCompany().getName());
				 }
				 principal.setCompany(company);
			 }else {
				 principal = null;
			 }
		}catch(Exception ex) {
			return ResponseEntity.ok(ex.getMessage());
		}
		return ResponseEntity.ok(principal);
	}
	
	
	@PostMapping(value = "register")
	public ResponseEntity<Void> registerEmployeeUser(
			@RequestBody User user) {
		user.setType(UserType.Employee);
		if(StringUtils.isNotBlank(user.getUserName()) && StringUtils.isNotBlank(user.getPassword())) {
			if(user.getCompany()!=null) {
				Company entity = user.getCompany();
				entity.setCompanyId(UUID.randomUUID().toString());
				
				Address entityAddress = entity.getAddress();
				entityAddress.setAddressId(UUID.randomUUID().toString());
				entity.setAddress(entityAddress);
				
				companyRepository.save(entity);
				user.setCompany(entity);
			}
			Address userAddress = user.getAddress();
			userAddress.setAddressId(UUID.randomUUID().toString());
			user.setAddress(userAddress);
			user.setUserId(UUID.randomUUID().toString());
			userRepository.save(user);
		}else {
			return new ResponseEntity<>(HttpStatus.LOCKED);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@GetMapping(value = "user/{company-id}")
	public ResponseEntity<List<User>> findAll(
			@PathVariable(name="company-id") Long companyId
			) {
		List<User> users = new ArrayList<User>();
		try {
			Company company = companyRepository.findOne(companyId);
			users = userRepository.findAllUsersByCompanyId(companyId);
		}catch(Exception ex) {
			System.out.println(ex.getMessage()); //company
		}
		return ResponseEntity.ok(users);
	}
	
	
	
}
