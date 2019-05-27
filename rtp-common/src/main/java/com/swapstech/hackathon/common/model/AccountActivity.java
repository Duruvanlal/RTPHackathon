/**
 * 
 */
package com.swapstech.hackathon.common.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Duruvanlal TJ
 *
 */
public class AccountActivity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
Status status;
Party party;
List<TransactionItem> items;
public Status getStatus() {
	return status;
}
public void setStatus(Status status) {
	this.status = status;
}
public Party getParty() {
	return party;
}
public void setParty(Party party) {
	this.party = party;
}
public List<TransactionItem> getItems() {
	return items;
}
public void setItems(List<TransactionItem> items) {
	this.items = items;
}
@Override
public String toString() {
	return "AccountActivity [status=" + status + ", party=" + party + ", items=" + items + "]";
}


}
