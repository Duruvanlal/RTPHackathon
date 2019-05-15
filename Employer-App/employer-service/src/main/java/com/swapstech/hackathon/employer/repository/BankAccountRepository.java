package com.swapstech.hackathon.employer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapstech.hackathon.employer.model.BankAccount;

public interface BankAccountRepository  extends JpaRepository<BankAccount, Long> {
	
	
	
}
