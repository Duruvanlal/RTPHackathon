package com.swapstech.hackathon.employer.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.swapstech.hackathon.employer.repository.util.JsonLocalDateTimeDeserializer;
import com.swapstech.hackathon.employer.repository.util.JsonLocalDateTimeSerializer;
import com.swapstech.hackathon.employer.repository.util.LocalDateTimeAttributeConverter;

@Entity
@Table(name = "INVOICE_MSTR")
public class InvoiceMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;	

//	@OneToMany(mappedBy = "invoiceMaster", cascade = CascadeType.ALL)
//	@JsonIgnore
	
	@NaturalId
	@Column(name = "invoice_ref_id", nullable = false)
	private String invoiceRefId;
	
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "invoiceMaster")
	private List<InvoiceDetail> invoiceDetail;

	@Column(name = "PO_NBR")
	private String poNumber;

	@Column(name = "INV_TERM")
	private String inventryTerm;

	@Column(name = "BUYER_USER_ID")
	private String buyerUserId;

	@Column(name = "SELLER_USER_ID")
	private String sellerUserId;
	
	@Column(name = "BUYER_UPA_ID")
	private String buyerUpaId;

	@Column(name = "SELLER_UPA_ID")
	private String sellerUpaId;

	@Column(name = "TOTAL_AMOUNT")
	private BigInteger totalAmount;
	
	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_DT")
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@JsonDeserialize(using = JsonLocalDateTimeDeserializer.class)
	@JsonSerialize(using = JsonLocalDateTimeSerializer.class)
	private LocalDateTime createdDateTime;

	@Column(name = "INV_SUBMIT_DT")
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@JsonDeserialize(using = JsonLocalDateTimeDeserializer.class)
	@JsonSerialize(using = JsonLocalDateTimeSerializer.class)
	private LocalDateTime inventorySubDateTime;

	@Column(name = "INV_DUE_DT")
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@JsonDeserialize(using = JsonLocalDateTimeDeserializer.class)
	@JsonSerialize(using = JsonLocalDateTimeSerializer.class)
	private LocalDateTime inventoryDueDateTime;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInvoiceRefId() {
		return invoiceRefId;
	}

	public void setInvoiceRefId(String invoiceRefId) {
		this.invoiceRefId = invoiceRefId;
	}

	public List<InvoiceDetail> getInvoiceDetail() {
		return invoiceDetail;
	}

	public void setInvoiceDetail(List<InvoiceDetail> invoiceDetail) {
		this.invoiceDetail = invoiceDetail;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public BigInteger getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigInteger totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getInventryTerm() {
		return inventryTerm;
	}

	public void setInventryTerm(String inventryTerm) {
		this.inventryTerm = inventryTerm;
	}

	public String getBuyerUserId() {
		return buyerUserId;
	}

	public void setBuyerUserId(String buyerUserId) {
		this.buyerUserId = buyerUserId;
	}

	public String getSellerUserId() {
		return sellerUserId;
	}

	public void setSellerUserId(String sellerUserId) {
		this.sellerUserId = sellerUserId;
	}

	public String getBuyerUpaId() {
		return buyerUpaId;
	}

	public void setBuyerUpaId(String buyerUpaId) {
		this.buyerUpaId = buyerUpaId;
	}

	public String getSellerUpaId() {
		return sellerUpaId;
	}

	public void setSellerUpaId(String sellerUpaId) {
		this.sellerUpaId = sellerUpaId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public LocalDateTime getInventorySubDateTime() {
		return inventorySubDateTime;
	}

	public void setInventorySubDateTime(LocalDateTime inventorySubDateTime) {
		this.inventorySubDateTime = inventorySubDateTime;
	}

	public LocalDateTime getInventoryDueDateTime() {
		return inventoryDueDateTime;
	}

	public void setInventoryDueDateTime(LocalDateTime inventoryDueDateTime) {
		this.inventoryDueDateTime = inventoryDueDateTime;
	}

}
