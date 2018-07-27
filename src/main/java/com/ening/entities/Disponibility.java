package com.ening.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="disponibilities")
public class Disponibility {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_disponibility;
	private String beginDate;
	private String endDate;
	@ManyToOne
	@JoinColumn(name = "id_practitioner")
	private Practitioner practitioner;
	
	
	public Disponibility() {
		super();
	}
	

	public long getId_disponibility() {
		return id_disponibility;
	}
	public void setId_disponibility(long id_disponibility) {
		this.id_disponibility = id_disponibility;
	}
	
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public Practitioner getPractitioner() {
		return practitioner;
	}
	public void setPractitioner(Practitioner practitioner) {
		this.practitioner = practitioner;
	}
	
	
	
	
}
