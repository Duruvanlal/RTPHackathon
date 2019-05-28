package com.swapstech.hackathon.employer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapstech.hackathon.employer.model.InvoiceCustomer;

public interface InvoiceCustomerRepository extends JpaRepository<InvoiceCustomer, Long> {

	List<InvoiceCustomer> findByUserId(String userId);

}

