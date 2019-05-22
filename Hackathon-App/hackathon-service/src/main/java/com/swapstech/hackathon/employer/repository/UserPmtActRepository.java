package com.swapstech.hackathon.employer.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.swapstech.hackathon.employer.model.UserPaymentAccount;

public interface UserPmtActRepository extends JpaRepository<UserPaymentAccount, Long> {
	
	public UserPaymentAccount findOneByUserIdAndAccountType(String userId,String accountType);
}
