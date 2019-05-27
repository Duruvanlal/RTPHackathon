/**
 * 
 */
package com.swapstech.hackathon.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.swapstech.hackathon.common.model.UserUpaMaster;

/**
 * @author Duruvanlal TJ
 *
 */
public interface UserUpaMasterRepository extends JpaRepository<UserUpaMaster, String> {
	public UserUpaMaster findByUpaCd(String upaCd);
}
