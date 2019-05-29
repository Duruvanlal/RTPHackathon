/**
 * 
 */
package com.swapstech.hackathon.common.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Convert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.swapstech.hackathon.common.repository.util.LocalDateTimeAttributeConverter;

/**
 * @author Duruvanlal TJ
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class RtpRfpDTO implements Serializable{
private static final long serialVersionUID = 7887612552844518073L;
	ValueObject creditAccountId;
	String debitAccountId;
	CurrencyAmount amount;
	String creditorName;
	String debtorName;
	String agentMemId;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	String externalReferenceId;
	String systemReferenceNo;
	
	String valueDate;
	String instructionId;
	public ValueObject getCreditAccountId() {
		return creditAccountId;
	}
	public void setCreditAccountId(ValueObject creditAccountId) {
		this.creditAccountId = creditAccountId;
	}
	public String getDebitAccountId() {
		return debitAccountId;
	}
	public void setDebitAccountId(String debitAccountId) {
		this.debitAccountId = debitAccountId;
	}
	public CurrencyAmount getAmount() {
		return amount;
	}
	public void setAmount(CurrencyAmount amount) {
		this.amount = amount;
	}
	public String getCreditorName() {
		return creditorName;
	}
	public void setCreditorName(String creditorName) {
		this.creditorName = creditorName;
	}
	public String getDebtorName() {
		return debtorName;
	}
	public void setDebtorName(String debtorName) {
		this.debtorName = debtorName;
	}
	public String getAgentMemId() {
		return agentMemId;
	}
	public void setAgentMemId(String agentMemId) {
		this.agentMemId = agentMemId;
	}
	public String getExternalReferenceId() {
		return externalReferenceId;
	}
	public void setExternalReferenceId(String externalReferenceId) {
		this.externalReferenceId = externalReferenceId;
	}
	public String getSystemReferenceNo() {
		return systemReferenceNo;
	}
	public void setSystemReferenceNo(String systemReferenceNo) {
		this.systemReferenceNo = systemReferenceNo;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}	
	
	public String getInstructionId() {
		return instructionId;
	}
	public void setInstructionId(String instructionId) {
		this.instructionId = instructionId;
	}
	@Override
	public String toString() {
		return "RtpRfpDTO [creditAccountId=" + creditAccountId + ", debitAccountId=" + debitAccountId + ", amount="
				+ amount + ", creditorName=" + creditorName + ", debtorName=" + debtorName + ", agentMemId="
				+ agentMemId + ", externalReferenceId=" + externalReferenceId + ", systemReferenceNo="
				+ systemReferenceNo + ", valueDate=" + valueDate + ", instructionId=" + instructionId + "]";
	}

	
}
