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
public class UserSessionDTOList implements Serializable{

	private static final long serialVersionUID = 1L;
	List<UserSessionDTO> userSessionDTOList;
	public List<UserSessionDTO> getUserSessionDTOList() {
		return userSessionDTOList;
	}
	public void setUserSessionDTOList(List<UserSessionDTO> userSessionDTOList) {
		this.userSessionDTOList = userSessionDTOList;
	}

}
