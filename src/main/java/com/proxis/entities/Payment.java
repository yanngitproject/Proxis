package com.proxis.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="payments")
public class Payment {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id_payment;
	private String type ;
	private String description;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "payments")
	private List<Practitioner> practitioners ;
		
	
	
	public Payment() {
		super();
	}
	
	public long getId_payment() {
		return id_payment;
	}
	public void setId_payment(long id_payment) {
		this.id_payment = id_payment;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public List<Practitioner> getPractitioners() {
		return practitioners;
	}

	public void setPractitioners(List<Practitioner> practitioners) {
		this.practitioners = practitioners;
	}
	
	

}
