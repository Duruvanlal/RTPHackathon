package com.swapstech.hackathon.employer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapstech.hackathon.employer.model.InvoiceDetail;



public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Long> {

}
