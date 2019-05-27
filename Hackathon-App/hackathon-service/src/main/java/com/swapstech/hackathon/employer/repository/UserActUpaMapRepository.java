package com.swapstech.hackathon.employer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapstech.hackathon.employer.model.UserActUpaMapping;

public interface UserActUpaMapRepository extends JpaRepository<UserActUpaMapping, Long> {
	
}
