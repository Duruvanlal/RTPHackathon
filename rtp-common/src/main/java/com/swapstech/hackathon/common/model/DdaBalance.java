/**
 * 
 */
package com.swapstech.hackathon.common.model;

import java.io.Serializable;

/**
 * @author Duruvanlal TJ
 *
 */
public class DdaBalance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ValueObject id;
	ValueObject partyId;
	String status;
	String ddaAccountType;
	CurrencyAmount availableBalance;
	CurrencyAmount currentBalance;
	public ValueObject getId() {
		return id;
	}
	public void setId(ValueObject id) {
		this.id = id;
	}
	public ValueObject getPartyId() {
		return partyId;
	}
	public void setPartyId(ValueObject partyId) {
		this.partyId = partyId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDdaAccountType() {
		return ddaAccountType;
	}
	public void setDdaAccountType(String ddaAccountType) {
		this.ddaAccountType = ddaAccountType;
	}
	
	public CurrencyAmount getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(CurrencyAmount availableBalance) {
		this.availableBalance = availableBalance;
	}
	public CurrencyAmount getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(CurrencyAmount currentBalance) {
		this.currentBalance = currentBalance;
	}
	@Override
	public String toString() {
		return "DdaBalance [id=" + id + ", partyId=" + partyId + ", status=" + status + ", ddaAccountType="
				+ ddaAccountType + ", availableBalance=" + availableBalance + ", currentBalance=" + currentBalance
				+ "]";
	}
	
	
	

}
