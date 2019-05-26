package com.swapstech.hackathon.employer.vo;

import java.io.Serializable;

public class EntityMasterClient implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String entityId;
	
	private String entityName;
	
	private String entityDesc;
	
	private String entityType;
	
	public AddressClient entityAddress;
	
	public UserClient user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getEntityDesc() {
		return entityDesc;
	}

	public void setEntityDesc(String entityDesc) {
		this.entityDesc = entityDesc;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public AddressClient getEntityAddress() {
		return entityAddress;
	}

	public void setEntityAddress(AddressClient entityAddress) {
		this.entityAddress = entityAddress;
	}

	public UserClient getUser() {
		return user;
	}

	public void setUser(UserClient user) {
		this.user = user;
	}


}
