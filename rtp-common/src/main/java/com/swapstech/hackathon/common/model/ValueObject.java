/**
 * 
 */
package com.swapstech.hackathon.common.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Duruvanlal TJ
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ValueObject implements Serializable{

	private static final long serialVersionUID = 6723767182550065624L;
	String displayValue;
	String value;
	public String getDisplayValue() {
		return displayValue;
	}
	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "ValueObject [displayValue=" + displayValue + ", value=" + value + "]";
	}
	

}
