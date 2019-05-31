/**
 * 
 */
package com.swapstech.hackathon.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapstech.hackathon.common.model.ZillTransaction;

/**
 * @author Duruvanlal TJ
 *
 */
@Repository
public interface ZillTransactionRepository extends JpaRepository<ZillTransaction, Long> {
	public ZillTransaction findByPaymentTransCode(String paymentTransCode);
	
	public ZillTransaction findByPaymentReasonRefId(String paymentReasonRefId);
	
	public List<ZillTransaction> findByInstructionIdIsNull();
}
