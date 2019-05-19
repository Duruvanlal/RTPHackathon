package com.swapstech.hackathon.common.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapstech.hackathon.common.model.Contractor;
import com.swapstech.hackathon.common.model.User;
import com.swapstech.hackathon.common.repository.ContractorRepository;
import com.swapstech.hackathon.common.repository.UserRepository;

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
		user.getContractorList().addAll(contractorList);
		userRepository.save(user);
	}
	
	public List<Contractor> findAllByCompanyId(String companyId){
		List<Contractor> list = contractorRepository.findAllByCompanyId(companyId);
		return list;
	}
	
}
