/**
 * 
 */
package com.swapstech.hackathon.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Duruvanlal TJ
 *
 */
@Entity
@Table(name = "ZILL_TRANSACTION")
public class ZillTransaction implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable=false)
	private Long id;
	@Column(name = "PYMT_TRANS_CD", nullable=false)
	String paymentTransCode;
	@Column(name = "PYMT_REASON_TYPE", nullable=false)
	String paymentReasonType;
	@Column(name = "PYMT_REASON_REF_ID", nullable=false)
	String paymentReasonRefId;
	@Column(name = "REQUESTOR_UPA_CD", nullable=false)
	String requestorUpaCd;
	@Column(name = "PAYER_UPA_CD", nullable=false)
	String payerUpaCd;
	@Column(name = "RTP_TRANS_ID", nullable=false)
	String rtpTransId;
	@Column(name = "CREATED_BY", nullable=false)
	String createdBy;
	@Column(name = "CREATED_DT", nullable=false)
	LocalDateTime createdDt;
	@Column(name = "instruction_id", nullable=false)
	String instructionId;
	@Transient
	BigDecimal paymentAmount;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPaymentTransCode() {
		return paymentTransCode;
	}
	public void setPaymentTransCode(String paymentTransCode) {
		this.paymentTransCode = paymentTransCode;
	}
	public String getPaymentReasonType() {
		return paymentReasonType;
	}
	public void setPaymentReasonType(String paymentReasonType) {
		this.paymentReasonType = paymentReasonType;
	}
	public String getPaymentReasonRefId() {
		return paymentReasonRefId;
	}
	public void setPaymentReasonRefId(String paymentReasonRefId) {
		this.paymentReasonRefId = paymentReasonRefId;
	}
	public String getRequestorUpaCd() {
		return requestorUpaCd;
	}
	public void setRequestorUpaCd(String requestorUpaCd) {
		this.requestorUpaCd = requestorUpaCd;
	}
	public String getPayerUpaCd() {
		return payerUpaCd;
	}
	public void setPayerUpaCd(String payerUpaCd) {
		this.payerUpaCd = payerUpaCd;
	}
	public String getRtpTransId() {
		return rtpTransId;
	}
	public void setRtpTransId(String rtpTransId) {
		this.rtpTransId = rtpTransId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(LocalDateTime createdDt) {
		this.createdDt = createdDt;
	}
	public String getInstructionId() {
		return instructionId;
	}
	public void setInstructionId(String instructionId) {
		this.instructionId = instructionId;
	}
	
	
	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	@Override
	public String toString() {
		return "ZillTransaction [id=" + id + ", paymentTransCode=" + paymentTransCode + ", paymentReasonType="
				+ paymentReasonType + ", paymentReasonRefId=" + paymentReasonRefId + ", requestorUpaCd="
				+ requestorUpaCd + ", payerUpaCd=" + payerUpaCd + ", rtpTransId=" + rtpTransId + ", createdBy="
				+ createdBy + ", createdDt=" + createdDt + ", instructionId=" + instructionId + ", paymentAmount="
				+ paymentAmount + "]";
	}
	
	

}
