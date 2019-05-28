package com.swapstech.hackathon.employer.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.swapstech.hackathon.employer.model.InvoiceDetail;
import com.swapstech.hackathon.employer.model.InvoiceMaster;
import com.swapstech.hackathon.employer.model.User;
import com.swapstech.hackathon.employer.repository.InvoiceDetailRepository;
import com.swapstech.hackathon.employer.repository.InvoiceMasterRepository;
import com.swapstech.hackathon.employer.repository.UserRepository;

@RestController
public class InvoiceController {
	private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceController.class);
	
	@Autowired
	InvoiceMasterRepository invoiceMasterRepository;
	
	@Autowired
	InvoiceDetailRepository invoiceDetailRepository;
	
	@PostMapping(value = "invoice")
	public ResponseEntity<Object> invoice(@RequestBody InvoiceMaster invoiceMaster) {
		
		
		
		Random generator = new Random();
		generator.setSeed(System.currentTimeMillis());
		int num = generator.nextInt(99999) + 99999;
		
		invoiceMaster.setInvoiceRefId("INVOICE_"+num);
		
		List<InvoiceDetail>invoiceDetailList = new ArrayList<InvoiceDetail>();
		
		for(InvoiceDetail invoiceDetail : invoiceMaster.getInvoiceDetail()) {	
			invoiceDetail.setInvoiceMaster(invoiceMaster);
			invoiceDetailList.add(invoiceDetail);
		}
		
		invoiceMaster.setInvoiceDetail(invoiceDetailList);
		
		try {
			invoiceMaster = invoiceMasterRepository.save(invoiceMaster);
		}catch(Exception e) {
			return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
		return ResponseEntity.ok(invoiceMaster);
		
	}
	
	@GetMapping(value="invoice/buyer/{buyerUserId}")
	public ResponseEntity<List<InvoiceMaster>> getBuyerInvoices(@PathVariable ("buyerUserId") String buyerUserId) {
		
		List<InvoiceMaster> invoiceMasterList= new ArrayList<InvoiceMaster>();
		
		invoiceMasterList = invoiceMasterRepository.findByBuyerUserId(buyerUserId);
		
		return ResponseEntity.ok(invoiceMasterList);
	}
	
	@GetMapping(value="invoice/seller/{sellerUserId}")
	public ResponseEntity<List<InvoiceMaster>> getSellerInvoices(@PathVariable ("sellerUserId") String sellerUserId) {
		
		List<InvoiceMaster> invoiceMasterList= new ArrayList<InvoiceMaster>();
		
		invoiceMasterList = invoiceMasterRepository.findBySellerUserId(sellerUserId);
		
		return ResponseEntity.ok(invoiceMasterList);
	}
}