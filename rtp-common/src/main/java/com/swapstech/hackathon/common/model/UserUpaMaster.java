/**
 * 
 */
package com.swapstech.hackathon.common.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author Duruvanlal TJ
 *
 */
@Entity
@Table(name = "user_upa_master")
public class UserUpaMaster implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_UPA_MSTR_ID", nullable=false)
	String userUpaMasterId;
	@Column(name = "USER_ID", nullable=false)
	String userId;
	@Column(name = "UPA_CD", nullable=false)
	String upaCd;
	
	public String getUserUpaMasterId() {
		return userUpaMasterId;
	}
	public void setUserUpaMasterId(String userUpaMasterId) {
		this.userUpaMasterId = userUpaMasterId;
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
	@Override
	public String toString() {
		return "UserUpaMaster [userUpaMasterId=" + userUpaMasterId + ", userId=" + userId + ", upaCd=" + upaCd + "]";
	}
	
	
}
