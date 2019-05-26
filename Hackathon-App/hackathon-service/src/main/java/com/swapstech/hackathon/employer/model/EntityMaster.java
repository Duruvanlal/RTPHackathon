package com.swapstech.hackathon.employer.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "ENTITY_MSTR")
public class EntityMaster implements Serializable{
	  
	  	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "ID", nullable=false)
		private Long id;
		
		
		@Column(name="ENTITY_ID")
		private String entityId;
		
		@Column(name = "ENTITY_NM")
		private String entityName;
		
		@Column(name = "ENTITY_DESC")
		private String entityDesc;
		
		@Column(name = "ENTITY_TYPE")
		private String entityType;
		
		//@JsonManagedReference	
		@OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
		public Address entityAddress;
		
		// @JsonBackReference
		 @JsonIgnore
		 @OneToOne(mappedBy = "entity")
		 public User user;

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

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Address getAddress() {
			return entityAddress;
		}

		public void setAddress(Address entityAddress) {
			this.entityAddress = entityAddress;
		}
		
		
		@Override
		public String toString() {

		    return getId().toString();
		}
		
}
