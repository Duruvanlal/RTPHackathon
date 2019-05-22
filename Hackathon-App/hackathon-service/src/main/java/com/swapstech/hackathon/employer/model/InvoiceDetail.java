package com.swapstech.hackathon.employer.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "INVOICE_DETAIL")
public class InvoiceDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;

	
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "invoice_ref_id",referencedColumnName = "invoice_ref_id", nullable = false)
	 private InvoiceMaster invoiceMaster;
	

	@Column(name = "PRODUCT_NM")
	private String productName;

	@Column(name = "PRODUCT_DESC")
	private String productDesc;

	@Column(name = "PRODUCT_QTY")
	private Long productQty;

	@Column(name = "UNIT_PRICE")
	private BigDecimal unitPrice;

	@Column(name = "TOTAL_AMT")
	private BigDecimal totalAmount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

//	public String getInvoiceRefId() {
//		return invoiceRefId;
//	}
//
//	public void setInvoiceRefId(String invoiceRefId) {
//		this.invoiceRefId = invoiceRefId;
//	}

	@JsonIgnore
	public InvoiceMaster getInvoiceMaster() {
		return invoiceMaster;
	}

	public void setInvoiceMaster(InvoiceMaster invoiceMaster) {
		this.invoiceMaster = invoiceMaster;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public Long getProductQty() {
		return productQty;
	}

	public void setProductQty(Long productQty) {
		this.productQty = productQty;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
}
