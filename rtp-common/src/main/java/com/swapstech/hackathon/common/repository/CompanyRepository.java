package com.swapstech.hackathon.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapstech.hackathon.common.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
