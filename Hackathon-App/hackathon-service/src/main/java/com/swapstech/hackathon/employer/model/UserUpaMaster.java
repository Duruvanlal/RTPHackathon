package com.swapstech.hackathon.employer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER_UPA_MASTER")
public class UserUpaMaster implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	@Column(name = "USER_UPA_MSTR_ID")
	private String userUpaMstrId;
	
	@Column(name = "USER_ID")
	private String userId;
	
	@Column(name = "UPA_CD")
	private String upaCd;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserUpaMstrId() {
		return userUpaMstrId;
	}

	public void setUserUpaMstrId(String userUpaMstrId) {
		this.userUpaMstrId = userUpaMstrId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUpaCd() {
		return upaCd;
	}

	public void setUpaCd(String upaCd) {
		this.upaCd = upaCd;
	}

}
