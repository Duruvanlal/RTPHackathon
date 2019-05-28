/**
 * 
 */
package com.swapstech.hackathon.common.model;

import java.io.Serializable;

/**
 * @author Duruvanlal TJ
 *
 */
public class AccountBalance implements Serializable{

	private static final long serialVersionUID = 1L;
	Status status;
	DdaBalance demandDepositAccountDTO;
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public DdaBalance getDemandDepositAccountDTO() {
		return demandDepositAccountDTO;
	}
	public void setDemandDepositAccountDTO(DdaBalance demandDepositAccountDTO) {
		this.demandDepositAccountDTO = demandDepositAccountDTO;
	}
	@Override
	public String toString() {
		return "AccountBalance [status=" + status + ", demandDepositAccountDTO=" + demandDepositAccountDTO + "]";
	}
	
	
}
