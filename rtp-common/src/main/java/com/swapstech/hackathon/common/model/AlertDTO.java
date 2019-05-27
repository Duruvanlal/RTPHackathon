/**
 * 
 */
package com.swapstech.hackathon.common.model;
import java.io.Serializable;
/**
 * @author Duruvanlal TJ
 *
 */
public class AlertDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	String messageBody;
	public String getMessageBody() {
		return messageBody;
	}
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	@Override
	public String toString() {
		return "AlertDTO [messageBody=" + messageBody + "]";
	}
	
	

}
