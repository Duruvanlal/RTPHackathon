/**
 * 
 */
package com.swapstech.hackathon.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapstech.hackathon.common.model.ZillTransaction;
import com.swapstech.hackathon.common.model.ZillTransactionDetails;

/**
 * @author Duruvanlal TJ
 *
 */
@Repository
public interface ZillTransactionDetailsRepository extends JpaRepository<ZillTransactionDetails, Long> {
	public ZillTransactionDetails findByPaymentTransCode(String paymentTransCode);

}
