package com.swapstech.hackathon.employer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapstech.hackathon.employer.model.EntityMaster;


public interface EntityRepository extends JpaRepository<EntityMaster, Long> {

}
