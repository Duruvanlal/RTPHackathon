package com.swapstech.hackathon.employer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapstech.hackathon.employer.model.Contractor;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {
	
	
	List<Contractor> findAllByCompanyId(String companyId);
	
}
