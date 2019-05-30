/**
 * 
 */
package com.swapstech.hackathon.common.model;

import java.io.Serializable;

/**
 * @author Duruvanlal TJ
 *
 */
public class RfpApprovalResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	Status status;
	
	RtpRfpDTO payinDetails;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	

	public RtpRfpDTO getPayinDetails() {
		return payinDetails;
	}

	public void setPayinDetails(RtpRfpDTO payinDetails) {
		this.payinDetails = payinDetails;
	}

	@Override
	public String toString() {
		return "RfpApprovalResponse [status=" + status + ", payinDetails=" + payinDetails + "]";
	}

	
	
	

}
