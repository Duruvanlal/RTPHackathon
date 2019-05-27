/**
 * 
 */
package com.swapstech.hackathon.common.model;

import java.io.Serializable;

/**
 * @author Duruvanlal TJ
 *
 */
public class TransactionItem implements Serializable {
private static final long serialVersionUID = 1L;
	String postingDate;
	String valueDate;
	String transactionDate;
	ValueObject accountId;
	CurrencyAmount amountInAccountCurrency;
	String userReferenceNumber;
	String transactionType;
	public String getPostingDate() {
		return postingDate;
	}
	public void setPostingDate(String postingDate) {
		this.postingDate = postingDate;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public ValueObject getAccountId() {
		return accountId;
	}
	public void setAccountId(ValueObject accountId) {
		this.accountId = accountId;
	}
	public CurrencyAmount getAmountInAccountCurrency() {
		return amountInAccountCurrency;
	}
	public void setAmountInAccountCurrency(CurrencyAmount amountInAccountCurrency) {
		this.amountInAccountCurrency = amountInAccountCurrency;
	}
	public String getUserReferenceNumber() {
		return userReferenceNumber;
	}
	public void setUserReferenceNumber(String userReferenceNumber) {
		this.userReferenceNumber = userReferenceNumber;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	@Override
	public String toString() {
		return "TransactionItem [postingDate=" + postingDate + ", valueDate=" + valueDate + ", transactionDate="
				+ transactionDate + ", accountId=" + accountId + ", amountInAccountCurrency=" + amountInAccountCurrency
				+ ", userReferenceNumber=" + userReferenceNumber + ", transactionType=" + transactionType + "]";
	}
	
	

}
