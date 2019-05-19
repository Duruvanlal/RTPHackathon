package com.swapstech.hackathon.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapstech.hackathon.common.model.BankAccount;

public interface BankAccountRepository  extends JpaRepository<BankAccount, Long> {
	
	
	
}
