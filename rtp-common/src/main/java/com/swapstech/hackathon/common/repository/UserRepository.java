package com.swapstech.hackathon.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapstech.hackathon.common.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findAllUserByUserNameAndPassword(String loginId, String password);
	User findOneByFirstNameAndLastNameAndUserName(String firstName,String lastName,String userName);
	List<User> findAllUsersByCompanyId(Long company);
	User findByUserId(String userId);
	
}
