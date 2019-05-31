package com.swapstech.hackathon.employer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.swapstech.hackathon.employer.model.UserPaymentAccount;

public interface UserPmtActRepository extends JpaRepository<UserPaymentAccount, Long> {
	
	public UserPaymentAccount findOneByUserIdAndAccountType(String userId,String accountType);
	
	public UserPaymentAccount findOneByUserIdAndBankName(String userId,String bankName);
	
	public List<UserPaymentAccount> findByUserId(String userId);
	
	public List<UserPaymentAccount> findByUserIdNotIn(String userId);
	
}
