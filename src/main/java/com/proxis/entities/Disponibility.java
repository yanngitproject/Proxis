package com.proxis.entities;

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
	private String date;
	private String beginHour;
	private String endHour;
	private String details;
	private int status;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getBeginHour() {
		return beginHour;
	}
	public void setBeginHour(String beginHour) {
		this.beginHour = beginHour;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Practitioner getPractitioner() {
		return practitioner;
	}
	public void setPractitioner(Practitioner practitioner) {
		this.practitioner = practitioner;
	}
	
	
	
	
}
