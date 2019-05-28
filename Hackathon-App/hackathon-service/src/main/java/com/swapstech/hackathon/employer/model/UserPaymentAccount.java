package com.swapstech.hackathon.employer.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.swapstech.hackathon.employer.repository.util.JsonLocalDateTimeDeserializer;
import com.swapstech.hackathon.employer.repository.util.JsonLocalDateTimeSerializer;
import com.swapstech.hackathon.employer.repository.util.LocalDateTimeAttributeConverter;

@Entity
@Table(name = "USER_PYMT_ACCT")
public class UserPaymentAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "USER_PYMT_ACCT_ID")
	private String userPmtActId;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "UPA_CD")
	private String upaCD;

	@Column(name = "ACCT_TYPE")
	private String accountType;
	
	
	@Column(name = "SHORT_NM")
	private String shortName;

	@Column(name = "ACCT_NM")
	private String accountName;

	@Column(name = "BANK_NM")
	private String bankName;
	
	@Column(name = "ACCT_NBR")
	private String accountNumber;

	@Column(name = "ROUTING_NBR")
	private String routingNumber;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_DT")
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@JsonDeserialize(using = JsonLocalDateTimeDeserializer.class)
	@JsonSerialize(using = JsonLocalDateTimeSerializer.class)
	private LocalDateTime createdDateTime;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "UPDATED_DT")
	@Convert(converter = LocalDateTimeAttributeConverter.class)
	@JsonDeserialize(using = JsonLocalDateTimeDeserializer.class)
	@JsonSerialize(using = JsonLocalDateTimeSerializer.class)
	private LocalDateTime updatedDateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserPmtActId() {
		return userPmtActId;
	}

	public void setUserPmtActId(String userPmtActId) {
		this.userPmtActId = userPmtActId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUpaCD() {
		return upaCD;
	}

	public void setUpaCD(String upaCD) {
		this.upaCD = upaCD;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
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

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(LocalDateTime createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(LocalDateTime updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

}
