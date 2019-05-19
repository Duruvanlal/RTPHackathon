package com.swapstech.hackathon.employer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapstech.hackathon.employer.model.Company;
import com.swapstech.hackathon.employer.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findAllUserByUserNameAndPassword(String loginId, String password);
	User findOneByFirstNameAndLastNameAndUserName(String firstName,String lastName,String userName);
	List<User> findAllUsersByCompanyId(Long company);
	
}
