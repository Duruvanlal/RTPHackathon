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
public class AlertList implements Serializable {


	private static final long serialVersionUID = 1L;
	
	Status status;
	List<AlertDTO> alertDTOs;
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public List<AlertDTO> getAlertDTOs() {
		return alertDTOs;
	}
	public void setAlertDTOs(List<AlertDTO> alertDTOs) {
		this.alertDTOs = alertDTOs;
	}
	@Override
	public String toString() {
		return "AlertList [status=" + status + ", alertDTOs=" + alertDTOs + "]";
	}

}
