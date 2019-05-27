/**
 * 
 */
package com.swapstech.hackathon.common.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.swapstech.hackathon.common.model.AccountActivity;
import com.swapstech.hackathon.common.model.RfpAction;
import com.swapstech.hackathon.common.model.TransactionItem;
import com.swapstech.hackathon.common.model.ZillTransaction;
import com.swapstech.hackathon.common.model.ZillTransactionDetails;
import com.swapstech.hackathon.common.service.ZillTransactionDetailsService;
import com.swapstech.hackathon.common.service.ZillTransactionService;

import io.swagger.annotations.ApiOperation;

/**
 * @author Duruvanlal TJ
 *
 */
@Controller
public class ZillTransactionController {
	@Autowired	
	ZillTransactionService service;
	@Autowired	
	ZillTransactionDetailsService detailService;
	@GetMapping(value = "/zill/transactions/ping")
	public ResponseEntity<Void> ping() {
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/zill/transactions", produces = "application/json")
	@ApiOperation(value = "Zill Transaction List", response = List.class)
	public ResponseEntity<List<ZillTransaction>> listAllZillTransations() {
		List<ZillTransaction> zillTransactions = service.listAllZillTransactions();
		return ResponseEntity.ok(zillTransactions);
	}
	
	@GetMapping(value = "/zill/transactions/details", produces = "application/json")
	@ApiOperation(value = "Zill Transaction List", response = List.class)
	public ResponseEntity<List<ZillTransactionDetails>> listAllZillTransationDetails() {
		List<ZillTransactionDetails> zillTransactions = detailService.listAllZillTransactionDetails();
		return ResponseEntity.ok(zillTransactions);
	}
	
	@PostMapping(value = "/zill/transactions", produces = "application/json")
	@ApiOperation(value = "Zill Transaction List", response = ZillTransaction.class)
	public ResponseEntity<ZillTransaction> createZillTransaction(@RequestBody ZillTransaction zillTransaction) {
		ZillTransaction newZillTransaction = service.createZillTransaction(zillTransaction);
		return ResponseEntity.ok(newZillTransaction);
	}
	
	@GetMapping(value = "/zill/transactions/trans-code/{trans-code}", produces = "application/json")
	@ApiOperation(value = "Zill Transaction by Id", response = ZillTransactionDetails.class)
	public ResponseEntity<ZillTransactionDetails> getZillTxnById(@PathVariable ("trans-code") String transCode) {
		ZillTransactionDetails zillTransaction = detailService.getZillTransactionDetailByTransCd(transCode);
		return ResponseEntity.ok(zillTransaction);
	}
	@GetMapping(value = "/zill/transactions/reason-ref-id/{reason-ref-id}", produces = "application/json")
	@ApiOperation(value = "Zill Transaction by Id", response = ZillTransaction.class)
	public ResponseEntity<ZillTransaction> getReasonRefId(@PathVariable ("reason-ref-id") String paymentReasonRefId) {
		ZillTransaction zillTransaction = service.getZillTransactionByReasonRefId(paymentReasonRefId);
		return ResponseEntity.ok(zillTransaction);
	}
	
	@PostMapping(value = "/zill/transactions/rfp-response", produces = "application/json")
	@ApiOperation(value = "Zill Transaction List", response = RfpAction.class)
	public ResponseEntity<ZillTransaction> makeRfpAction(@RequestBody RfpAction action) {
		ZillTransaction newZillTransaction = service.makeRfpAction(action);
		return ResponseEntity.ok(newZillTransaction);
	}
	
	@GetMapping(value = "/zill/transactions/account/{acct-num}", produces = "application/json")
	@ApiOperation(value = "Zill Transaction by Id", response = AccountActivity.class)
	public ResponseEntity<List<TransactionItem>> getRtpTrasnactions(@PathVariable ("acct-num") String acctNum) {
		List<TransactionItem> accountActivity =service.getRtpTransactions(acctNum);
		return ResponseEntity.ok(accountActivity);
	}
}
