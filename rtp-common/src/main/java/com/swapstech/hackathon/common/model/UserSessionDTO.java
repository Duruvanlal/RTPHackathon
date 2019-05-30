/**
 * 
 */
package com.swapstech.hackathon.common.model;

import java.io.Serializable;

/**
 * @author Duruvanlal TJ
 *
 */
public class UserSessionDTO implements Serializable{
private static final long serialVersionUID = 1L;
	String creationDate;
	String lastUpdatedDate;
	String version;
	String generatedPackageId;
	String auditSequence;
	String ipAddress;
	String channel;
	String userName;
	String sessionId;
	String statusFlag;
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getGeneratedPackageId() {
		return generatedPackageId;
	}
	public void setGeneratedPackageId(String generatedPackageId) {
		this.generatedPackageId = generatedPackageId;
	}
	public String getAuditSequence() {
		return auditSequence;
	}
	public void setAuditSequence(String auditSequence) {
		this.auditSequence = auditSequence;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getStatusFlag() {
		return statusFlag;
	}
	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}
	@Override
	public String toString() {
		return "userSessionDTO [creationDate=" + creationDate + ", lastUpdatedDate=" + lastUpdatedDate + ", version="
				+ version + ", generatedPackageId=" + generatedPackageId + ", auditSequence=" + auditSequence
				+ ", ipAddress=" + ipAddress + ", channel=" + channel + ", userName=" + userName + ", sessionId="
				+ sessionId + ", statusFlag=" + statusFlag + "]";
	}
	
	
}
