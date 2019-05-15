package com.swapstech.hackathon.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapstech.hackathon.employee.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
