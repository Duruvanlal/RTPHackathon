/**
 * 
 */
package com.swapstech.hackathon.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapstech.hackathon.common.model.UserAcctUpaMapping;

/**
 * @author Duruvanlal TJ
 *
 */
public interface UserAcctUpaMappingRepository extends JpaRepository<UserAcctUpaMapping, String> {
	public UserAcctUpaMapping findByUserUpaMasterId(String userUpaMasterId);
}
