package com.swapstech.hackathon.employer.vo;

import java.io.Serializable;

import com.swapstech.hackathon.employer.model.Address;
import com.swapstech.hackathon.employer.model.EntityMaster;
import com.swapstech.hackathon.employer.model.User;

public class LoginUserRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
