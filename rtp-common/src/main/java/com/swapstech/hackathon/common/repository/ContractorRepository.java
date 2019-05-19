package com.swapstech.hackathon.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapstech.hackathon.common.model.Contractor;

public interface ContractorRepository extends JpaRepository<Contractor, Long> {
	
	
	List<Contractor> findAllByCompanyId(String companyId);
	
}
