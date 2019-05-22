/**
 * 
 */
package com.swapstech.hackathon.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

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

/**
 * @author Duruvanlal TJ
 *
 */
@Entity
@Table(name = "transaction_det")
public class ZillTransactionDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable=false)
	private long id;
	
	@ManyToOne
    @JoinColumn(name = "PYMT_TRANS_CD", nullable = false)
    //@JsonIgnore
    private ZillTransaction paymentTransCode;
	
	@Column(name = "TRANS_TYPE", nullable=false)
	String transType;
	
	@Column(name = "TRANS_DT", nullable=false)
	LocalDate transDate;
	
	@Column(name = "PYMT_AMT", nullable=false)
	BigDecimal paymentAmount;
	
	@Column(name = "PYMT_CCY", nullable=false)
	String paymentCurrency;
	
	@Column(name = "TRANS_DATA", nullable=true)
	String transData;
	
	@Column(name = "TRANS_STATUS", nullable=false)
	String transStatus;
	
	@Column(name = "TRANS_RESP_CD", nullable=true)
	String transRespCd;
	
	@Column(name = "TRANS_RESP_DESC", nullable=true)
	String transRespDesc;
	
	@Column(name = "TRANS_RESP_ERR_CD", nullable=true)
	String transErrorCd;
	
	@Column(name = "TRANS_RESP_ERR_DESC", nullable=true)
	String transErrorDesc;

	

	public ZillTransaction getPaymentTransCode() {
		return paymentTransCode;
	}

	public void setPaymentTransCode(ZillTransaction paymentTransCode) {
		this.paymentTransCode = paymentTransCode;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public LocalDate getTransDate() {
		return transDate;
	}

	public void setTransDate(LocalDate transDate) {
		this.transDate = transDate;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getPaymentCurrency() {
		return paymentCurrency;
	}

	public void setPaymentCurrency(String paymentCurrency) {
		this.paymentCurrency = paymentCurrency;
	}

	public String getTransData() {
		return transData;
	}

	public void setTransData(String transData) {
		this.transData = transData;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public String getTransRespCd() {
		return transRespCd;
	}

	public void setTransRespCd(String transRespCd) {
		this.transRespCd = transRespCd;
	}

	public String getTransRespDesc() {
		return transRespDesc;
	}

	public void setTransRespDesc(String transRespDesc) {
		this.transRespDesc = transRespDesc;
	}

	public String getTransErrorCd() {
		return transErrorCd;
	}

	public void setTransErrorCd(String transErrorCd) {
		this.transErrorCd = transErrorCd;
	}

	public String getTransErrorDesc() {
		return transErrorDesc;
	}

	public void setTransErrorDesc(String transErrorDesc) {
		this.transErrorDesc = transErrorDesc;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ZillTransactionDetails [id=" + id + ", paymentTransCode=" + paymentTransCode + ", transType=" + transType
				+ ", transDate=" + transDate + ", paymentAmount=" + paymentAmount + ", paymentCurrency="
				+ paymentCurrency + ", transData=" + transData + ", transStatus=" + transStatus + ", transRespCd="
				+ transRespCd + ", transRespDesc=" + transRespDesc + ", transErrorCd=" + transErrorCd
				+ ", transErrorDesc=" + transErrorDesc + "]";
	}
	
	

}
