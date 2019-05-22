/**
 * 
 */
package com.swapstech.hackathon.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapstech.hackathon.common.model.UserPaymentAccount;

/**
 * @author Duruvanlal TJ
 *
 */
@Repository
public interface UserPymtAcctRepository extends JpaRepository<UserPaymentAccount, Long> {
	public UserPaymentAccount findByUpaCd(String upaCd);
}
