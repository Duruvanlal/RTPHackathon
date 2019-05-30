/**
 * 
 */
package com.swapstech.hackathon.common.model;

import java.io.Serializable;

/**
 * @author Duruvanlal TJ
 *
 */
public class RfpResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	Status status;
	
	RtpRfpDTO usDomesticPayinInstructionDTO;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public RtpRfpDTO getUsDomesticPayinInstructionDTO() {
		return usDomesticPayinInstructionDTO;
	}

	public void setUsDomesticPayinInstructionDTO(RtpRfpDTO usDomesticPayinInstructionDTO) {
		this.usDomesticPayinInstructionDTO = usDomesticPayinInstructionDTO;
	}

	@Override
	public String toString() {
		return "RfpResponse [status=" + status + ", usDomesticPayinInstructionDTO=" + usDomesticPayinInstructionDTO
				+ "]";
	}

	
	
	

}
