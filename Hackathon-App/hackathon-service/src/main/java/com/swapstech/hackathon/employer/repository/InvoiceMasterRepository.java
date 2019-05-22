package com.swapstech.hackathon.employer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapstech.hackathon.employer.model.InvoiceMaster;



public interface InvoiceMasterRepository extends JpaRepository<InvoiceMaster, Long> {

	public List<InvoiceMaster> findByBuyerUserId(String buyerUserId);
	public List<InvoiceMaster> findBySellerUserId(String sellerUserId);
	
}
