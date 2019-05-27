package com.swapstech.hackathon.employer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.swapstech.hackathon.employer.model.UserUpaMaster;

public interface UserUpaMastRepository extends JpaRepository<UserUpaMaster, Long> {

	List<UserUpaMaster> findByUserId(String userId);

}
