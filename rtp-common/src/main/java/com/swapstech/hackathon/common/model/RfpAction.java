/**
 * 
 */
package com.swapstech.hackathon.common.model;

import java.io.Serializable;

/**
 * @author Duruvanlal TJ
 *
 */
public class RfpAction implements Serializable {
	static final long serialVersionUID = 1L;

	String referenceId;
	RfpResponseEnum action;
	String rejectReason;

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public RfpResponseEnum getAction() {
		return action;
	}

	public void setAction(RfpResponseEnum action) {
		this.action = action;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	@Override
	public String toString() {
		return "RfpAction [referenceId=" + referenceId + ", action=" + action + ", rejectReason=" + rejectReason + "]";
	}
}
