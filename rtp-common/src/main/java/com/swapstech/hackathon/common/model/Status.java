/**
 * 
 */
package com.swapstech.hackathon.common.model;

import java.io.Serializable;

/**
 * @author Duruvanlal TJ
 *
 */
public class Status implements Serializable{

	private static final long serialVersionUID = 2296705618368114912L;
String result;

public String getResult() {
	return result;
}

public void setResult(String result) {
	this.result = result;
}

@Override
public String toString() {
	return "Status [result=" + result + "]";
}

}
