package com.swapstech.hackathon.common.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "USER")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable=false)
	private Long id;
	
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name = "USER_NM")
	private String userName;
	
	@Column(name = "FIRST_NM")
	private String firstName;
	
	@Column(name = "MIDDLE_NM")
	private String middleName;

	@Column(name = "LAST_NM")
	private String lastName;

	@Column(name = "PASSWD")
	private String password;
	
	@Column(name = "USER_TYPE")
	@Enumerated(EnumType.STRING)
	private UserType type = UserType.Employee;
	
	@Column(name = "PHONE_NBR")
	private String phone;
	
	@Column(name = "EMAIL_ADDR")
	private String email;
	
	@Column(name = "STATUS")
	private String status;

	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDESS_ID", referencedColumnName = "id")
	private Address address;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "ENTITY_ID", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Company company;
	
	
	/* @ManyToMany
	    @JoinTable(name="USER_ENTITY",
	        joinColumns=
	            @JoinColumn(name="ENTITY_ID", referencedColumnName="ID"),
	        inverseJoinColumns=
	            @JoinColumn(name="USER_ID", referencedColumnName="ID")
	        )
	 public List<Company> companies;*/
	
	
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "USER_TIMESHEET_ID")
	private List<Contractor> contractorList = new ArrayList<>();
	
	
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Contractor> getContractorList() {
		return contractorList;
	}

	public void setContractorList(List<Contractor> contractorList) {
		this.contractorList = contractorList;
	}
	
	
}
