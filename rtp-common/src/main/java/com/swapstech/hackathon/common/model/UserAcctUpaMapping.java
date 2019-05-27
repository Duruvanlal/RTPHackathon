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
@Table(name = "user_acct_upa_mapping")
public class UserAcctUpaMapping implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "USER_UPA_MSTR_ID", nullable=false)
	String userUpaMasterId;
	@Column(name = "USER_PYMT_ACCT_ID", nullable=false)
	String userPaymentAcctId;
	public String getUserUpaMasterId() {
		return userUpaMasterId;
	}
	public void setUserUpaMasterId(String userUpaMasterId) {
		this.userUpaMasterId = userUpaMasterId;
	}
	public String getUserPaymentAcctId() {
		return userPaymentAcctId;
	}
	public void setUserPaymentAcctId(String userPaymentAcctId) {
		this.userPaymentAcctId = userPaymentAcctId;
	}
	@Override
	public String toString() {
		return "UserAcctUpaMapping [userUpaMasterId=" + userUpaMasterId + ", userPaymentAcctId=" + userPaymentAcctId
				+ "]";
	}
	
	
	
	
}
