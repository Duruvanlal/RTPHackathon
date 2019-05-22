/**
 * 
 */
package com.swapstech.hackathon.common.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapstech.hackathon.common.model.ZillTransaction;
import com.swapstech.hackathon.common.model.ZillTransactionDetails;
import com.swapstech.hackathon.common.repository.ZillTransactionDetailsRepository;
import com.swapstech.hackathon.common.repository.ZillTransactionRepository;

/**
 * @author Duruvanlal TJ
 *
 */
@Service
public class ZillTransactionDetailsService {
	
	@Autowired
	ZillTransactionDetailsRepository repository;
	@Autowired
	ZillTransactionRepository zillRepository;
	public List<ZillTransactionDetails> listAllZillTransactionDetails(){
		return repository.findAll();		
	}
	
	public ZillTransactionDetails getZillTransactionDetailById(long id){
		return repository.findOne(id);		
	}
	
	public ZillTransactionDetails getZillTransactionDetailByTransCd(String paymentTransCode){
		ZillTransaction txn=zillRepository.findByPaymentTransCode(paymentTransCode);
		return repository.findByPaymentTransCode(txn);
	}
	
	
	public ZillTransactionDetails createZillTransactionDetails(ZillTransactionDetails zillTransactionDetails){		
		return repository.save(zillTransactionDetails);
	}
	
	public ZillTransactionDetails updateZillTransactionDetails(ZillTransactionDetails zillTransaction){
		return repository.save(zillTransaction);
	}

}
