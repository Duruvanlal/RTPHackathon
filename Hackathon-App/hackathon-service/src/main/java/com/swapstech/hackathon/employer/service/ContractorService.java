package com.swapstech.hackathon.employer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapstech.hackathon.employer.model.Contractor;
import com.swapstech.hackathon.employer.model.User;
import com.swapstech.hackathon.employer.repository.ContractorRepository;
import com.swapstech.hackathon.employer.repository.UserRepository;

@Service
public class ContractorService {
	
	
	@Autowired
	ContractorRepository contractorRepository;
	@Autowired
	UserRepository userRepository;
	
	public void hireContractor(Contractor contractor, User requestedUser) throws Exception{
		User user = userRepository.findOneByFirstNameAndLastNameAndUserName(requestedUser.getFirstName(), requestedUser.getLastName(), requestedUser.getUserName());
		contractor.setCreatedDate(new Date());
		contractor.setUserId(user.getUserId());
		List<Contractor> contractorList = new ArrayList<>();
		contractorList.add(contractor);
		userRepository.save(user);
	}
	
	public List<Contractor> findAllByCompanyId(String companyId){
		List<Contractor> list = contractorRepository.findAllByCompanyId(companyId);
		return list;
	}
	
}
