package com.swapstech.hackathon.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapstech.hackathon.employee.model.BankAccount;

public interface BankAccountRepository  extends JpaRepository<BankAccount, Long> {
	
	
	
}
