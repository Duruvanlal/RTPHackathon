package com.swapstech.hackathon.employer.vo;

import java.io.Serializable;

public class AddressClient implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	public String addressId;
	
	private String address1;
	
	private String address2;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private String postalCode;
	
	public UserClient user;
	
	public EntityMasterClient entity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public UserClient getUser() {
		return user;
	}

	public void setUser(UserClient user) {
		this.user = user;
	}

	public EntityMasterClient getEntity() {
		return entity;
	}

	public void setEntity(EntityMasterClient entity) {
		this.entity = entity;
	}


}
