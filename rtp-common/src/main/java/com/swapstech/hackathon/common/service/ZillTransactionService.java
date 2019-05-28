/**
 * 
 */
package com.swapstech.hackathon.common.service;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapstech.hackathon.common.model.AccountActivity;
import com.swapstech.hackathon.common.model.AccountBalance;
import com.swapstech.hackathon.common.model.RfpAction;
import com.swapstech.hackathon.common.model.RfpResponseEnum;
import com.swapstech.hackathon.common.model.RtpRfpDTO;
import com.swapstech.hackathon.common.model.TransactionItem;
import com.swapstech.hackathon.common.model.ZillTransaction;
import com.swapstech.hackathon.common.model.ZillTransactionDetails;
import com.swapstech.hackathon.common.model.ZillTransactionStatus;
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
	ZillTransactionDetailsService detailsRepository;
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
		ZillTransactionDetails txnDetails=new ZillTransactionDetails();
		txnDetails.setPaymentTransCode(zillTransaction.getPaymentTransCode());
		txnDetails.setPaymentAmount(zillTransaction.getPaymentAmount());
		txnDetails.setPaymentCurrency("USD");
		txnDetails.setTransStatus(ZillTransactionStatus.SUBMITTED.name());
		txnDetails.setTransType("RFP");
		detailsRepository.createZillTransactionDetails(txnDetails);
		RtpRfpDTO rfpTxn=oracleService.makeRfpRequest(newZillTransaction);
		newZillTransaction.setRtpTransId(rfpTxn.getSystemReferenceNo());
		newZillTransaction=updateZillTransaction(newZillTransaction);
		txnDetails.setTransStatus(ZillTransactionStatus.TRANSMITTED.name());
		detailsRepository.updateZillTransactionDetails(txnDetails);
		return newZillTransaction;
	}
	
	public ZillTransaction updateZillTransaction(ZillTransaction zillTransaction){
		return repository.save(zillTransaction);
	}
	
	public ZillTransaction makeRfpAction(RfpAction action) {
		ZillTransaction txn = null;
		RtpRfpDTO rtpRfpDTO=null;
		ZillTransactionDetails txnDetails=new ZillTransactionDetails();
		if (action != null) {
			txn=getZillTransactionByReasonRefId(action.getReferenceId());
			if (action.getAction() != null
					&& action.getAction().name().equalsIgnoreCase(RfpResponseEnum.APPROVE.name())) {
				rtpRfpDTO=oracleService.approveRfp(txn);
				txnDetails=detailsRepository.getZillTransactionDetailByTransCd(txn.getPaymentTransCode());
				txnDetails.setTransStatus(ZillTransactionStatus.APPROVED.name());
				detailsRepository.updateZillTransactionDetails(txnDetails);
			} else {
				rtpRfpDTO=oracleService.rejectRfp(txn);
			}
			txnDetails=detailsRepository.getZillTransactionDetailByTransCd(txn.getPaymentTransCode());
			txnDetails.setTransStatus(ZillTransactionStatus.REJECTED.name());
			txnDetails.setTransErrorDesc(action.getRejectReason());
			detailsRepository.updateZillTransactionDetails(txnDetails);
		}
		if (rtpRfpDTO != null) {
			
		}
		return txn;
	}
	
	public List<TransactionItem> getRtpTransactions(String acctNumber){
		return oracleService.getRtpTransactions(acctNumber);
	}
	
	public AccountBalance getAcctBalance(String acctNumber){
		return oracleService.getBalance(acctNumber);
	}
}
