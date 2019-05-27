package com.swapstech.hackathon.employer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ACCT_UPA_MAPPING")
public class UserActUpaMapping implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "USER_UPA_MSTR_ID")
	private String userUPAMstrId;
	
	@Column(name = "USER_PYMT_ACCT_ID")
	private String userPymtAcctId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserUPAMstrId() {
		return userUPAMstrId;
	}

	public void setUserUPAMstrId(String userUPAMstrId) {
		this.userUPAMstrId = userUPAMstrId;
	}

	public String getUserPymtAcctId() {
		return userPymtAcctId;
	}

	public void setUserPymtAcctId(String userPymtAcctId) {
		this.userPymtAcctId = userPymtAcctId;
	}

}
