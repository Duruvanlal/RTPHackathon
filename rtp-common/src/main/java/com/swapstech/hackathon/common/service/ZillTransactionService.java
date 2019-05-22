/**
 * 
 */
package com.swapstech.hackathon.common.service;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapstech.hackathon.common.model.RfpAction;
import com.swapstech.hackathon.common.model.RfpResponseEnum;
import com.swapstech.hackathon.common.model.RtpRfpDTO;
import com.swapstech.hackathon.common.model.ZillTransaction;
import com.swapstech.hackathon.common.repository.ZillTransactionRepository;

/**
 * @author Duruvanlal TJ
 *
 */
@Service
public class ZillTransactionService {
	@Autowired
	ZillTransactionRepository repository;
	@Autowired
	OracleService oracleService;
	public List<ZillTransaction> listAllZillTransactions(){
		return repository.findAll();		
	}
	
	public ZillTransaction getZillTransactionById(long id){
		return repository.findOne(id);		
	}
	
	public ZillTransaction getZillTransactionByTransCd(String paymentTransCode){
		return repository.findByPaymentTransCode(paymentTransCode);
	}
	
	public ZillTransaction getZillTransactionByReasonRefId(String paymentReasonRefId){
		return repository.findByPaymentReasonRefId(paymentReasonRefId);
	}
	
	public ZillTransaction createZillTransaction(ZillTransaction zillTransaction){
		Random random=new Random();
		zillTransaction.setPaymentTransCode("Zill"+random.nextInt(999999));
		ZillTransaction newZillTransaction= repository.save(zillTransaction);
		RtpRfpDTO rfpTxn=oracleService.makeRfpRequest(newZillTransaction);
		newZillTransaction.setRtpTransId(rfpTxn.getSystemReferenceNo());
		newZillTransaction=updateZillTransaction(newZillTransaction);
		return newZillTransaction;
	}
	
	public ZillTransaction updateZillTransaction(ZillTransaction zillTransaction){
		return repository.save(zillTransaction);
	}
	
	public ZillTransaction makeRfpAction(RfpAction action) {
		ZillTransaction txn = null;
		if (action != null) {
			txn=getZillTransactionByReasonRefId(action.getReferenceId());
			if (action.getAction() != null
					&& action.getAction().name().equalsIgnoreCase(RfpResponseEnum.APPROVE.name())) {
				oracleService.approveRfp(txn);
			} else {
				oracleService.rejectRfp(txn);
			}
		}

		return txn;
	}
}
