/**
 * 
 */
package com.swapstech.hackathon.common.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Duruvanlal TJ
 *
 */
@Entity
@Table(name = "USER_PYMT_ACCT")
public class UserPaymentAccount implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable=false)
	private Long id;
	@Column(name = "USER_PYMT_ACCT_ID", nullable=false)
	private String userPaymentAcctId;
	@Column(name = "USER_ID", nullable=false)
	private String userId;
	@Column(name = "ACCT_TYPE", nullable=false)
	private String acctType;
	@Column(name = "SHORT_NM", nullable=false)
	private String shortName;
	@Column(name = "ACCT_NM", nullable=false)
	private String accountName;
	@Column(name = "BANK_NM", nullable=false)
	private String bankName;
	@Column(name = "ACCT_NBR", nullable=false)
	private String accountNumber;
	@Column(name = "ROUTING_NBR", nullable=false)
	private String routingNumber;
	@Column(name = "CREATED_BY", nullable=false)
	private String createdBy;
	@Column(name = "CREATED_DT", nullable=false)
	private LocalDate createdDate;
	@Column(name = "UPDATED_BY", nullable=false)
	private String updatedBy;
	@Column(name = "UPDATED_DT", nullable=false)
	private LocalDate updatedDate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUserPaymentAcctId() {
		return userPaymentAcctId;
	}
	public void setUserPaymentAcctId(String userPaymentAcctId) {
		this.userPaymentAcctId = userPaymentAcctId;
	}
	public String getAcctType() {
		return acctType;
	}
	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getRoutingNumber() {
		return routingNumber;
	}
	public void setRoutingNumber(String routingNumber) {
		this.routingNumber = routingNumber;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public LocalDate getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}
	@Override
	public String toString() {
		return "UserPaymentAccount [id=" + id + ", userPaymentAcctId=" + userPaymentAcctId + ", userId=" + userId
				+ ", acctType=" + acctType + ", shortName=" + shortName + ", accountName=" + accountName + ", bankName="
				+ bankName + ", accountNumber=" + accountNumber + ", routingNumber=" + routingNumber + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
				+ updatedDate + "]";
	}
	
	
}
