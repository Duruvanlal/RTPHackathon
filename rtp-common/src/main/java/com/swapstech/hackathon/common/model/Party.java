/**
 * 
 */
package com.swapstech.hackathon.common.model;

import java.io.Serializable;

/**
 * @author Duruvanlal TJ
 *
 */
public class Party implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
ValueObject partyId;
String partyName;
public ValueObject getPartyId() {
	return partyId;
}
public void setPartyId(ValueObject partyId) {
	this.partyId = partyId;
}
public String getPartyName() {
	return partyName;
}
public void setPartyName(String partyName) {
	this.partyName = partyName;
}
@Override
public String toString() {
	return "Party [partyId=" + partyId + ", partyName=" + partyName + "]";
}

}
