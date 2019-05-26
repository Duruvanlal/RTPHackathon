package com.swapstech.hackathon.employer.vo;

import java.io.Serializable;

import com.swapstech.hackathon.employer.model.Address;
import com.swapstech.hackathon.employer.model.EntityMaster;
import com.swapstech.hackathon.employer.model.UserType;

public class UserClient implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String userId;

	private String userName;

	private String firstName;

	private String middleName;

	private String lastName;

	private String password;

	private UserType type = UserType.Employee;

	private String phone;

	private String email;

	private String status;

	private String createdBy;

	private String updatedBy;
	
	public AddressClient address;
	
	public EntityMasterClient entity;
 
	private Boolean sameAddressAsEntity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public AddressClient getAddress() {
		return address;
	}

	public void setAddress(AddressClient address) {
		this.address = address;
	}

	public EntityMasterClient getEntity() {
		return entity;
	}

	public void setEntity(EntityMasterClient entity) {
		this.entity = entity;
	}

	public Boolean getSameAddressAsEntity() {
		return sameAddressAsEntity;
	}

	public void setSameAddressAsEntity(Boolean sameAddressAsEntity) {
		this.sameAddressAsEntity = sameAddressAsEntity;
	}

	
}
