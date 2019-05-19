package com.swapstech.hackathon.employer.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swapstech.hackathon.employer.model.Contractor;
import com.swapstech.hackathon.employer.model.User;
import com.swapstech.hackathon.employer.service.ContractorService;

@CrossOrigin
@RestController
public class ContractorController {
	
	@Autowired
	ContractorService contractorService;
	
	@PostMapping(value = "contractor/hire/{first-name}/{last-name}/{user-name}")
	public ResponseEntity<Boolean> hireContractor(
			@RequestBody Contractor contractor, 
			@PathVariable(name="user-name") String userName,
			@PathVariable(name="first-name") String firstName,
			@PathVariable(name="last-name") String lastName
			) {
		Boolean result = false;
		try {
			User user = new User();
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setUserName(userName);
			contractorService.hireContractor(contractor,user);
			result = true;
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value = "contractor/{company-id}")
	public ResponseEntity<List<Contractor>> findAll(
			@PathVariable(name="company-id") String companyId
			) {
		List<Contractor> contractors = new ArrayList<Contractor>();
		try {
			contractors = contractorService.findAllByCompanyId(companyId);
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return ResponseEntity.ok(contractors);
	}
	
	
	
}
