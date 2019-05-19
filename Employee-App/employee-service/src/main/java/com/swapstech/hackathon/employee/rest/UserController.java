package com.swapstech.hackathon.employee.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swapstech.hackathon.common.model.Company;
import com.swapstech.hackathon.common.model.User;
import com.swapstech.hackathon.common.model.UserType;
import com.swapstech.hackathon.common.repository.CompanyRepository;
import com.swapstech.hackathon.common.repository.UserRepository;

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
			List<User> userList =  userRepository.findAllUserByUserNameAndPassword(user.getUserId(), user.getPassword());
			 if(userList.size()>0) {
				 principal.setId(userList.get(0).getId());
				 principal.setFirstName(userList.get(0).getFirstName());
				 principal.setLastName(userList.get(0).getLastName());
				 principal.setEmail(userList.get(0).getEmail());
				 principal.setPhone(userList.get(0).getPhone());
				 principal.setUserId(userList.get(0).getUserId());
				 Company company = new Company();
				 if(userList.get(0).getCompany() != null) {
					 company.setId(userList.get(0).getCompany().getId());
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
			userRepository.save(user);
		}else {
			return new ResponseEntity<>(HttpStatus.LOCKED);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping(value = "users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> userList = new ArrayList<User>();
		try {
			List<User> list = userRepository.findAll();
			for(User user: list) {
				User u = new User();
				u.setId(user.getId());
				u.setEmail(user.getEmail());
				u.setFirstName(user.getFirstName());
				u.setLastName(user.getLastName());
				u.setUserId(user.getUserId());
				if(user.getCompany() != null) {
					Company company = new Company();
					company.setId(user.getCompany().getId());
					company.setAddress(user.getCompany().getAddress());
					company.setName(user.getCompany().getName());
					u.setCompany(company);
				}
				
				
				userList.add(u);
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return ResponseEntity.ok(userList);
	}
	
	
	
	
}
