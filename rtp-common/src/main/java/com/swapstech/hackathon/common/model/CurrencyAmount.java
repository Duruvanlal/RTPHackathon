/**
 * 
 */
package com.swapstech.hackathon.common.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Duruvanlal TJ
 *
 */
public class CurrencyAmount implements Serializable {

	private static final long serialVersionUID = -499518883610087993L;
	String currency;
	BigDecimal amount;
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "CurrencyAmount [currency=" + currency + ", amount=" + amount + "]";
	}
	
	

}
