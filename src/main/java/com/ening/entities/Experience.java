package com.ening.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="experiences")
public class Experience {
	
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_experience;
	private String date_begin;
	private String date_end;
	private String workplace;
	private String position_held;
	private String speciality;
	@ManyToOne
	@JoinColumn(name = "id_practitioner")
	private Practitioner practitioner;
	public long getId_experience() {
		return id_experience;
	}
	public void setId_experience(long id_experience) {
		this.id_experience = id_experience;
	}
	public String getDate_begin() {
		return date_begin;
	}
	public void setDate_begin(String date_begin) {
		this.date_begin = date_begin;
	}
	public String getDate_end() {
		return date_end;
	}
	public void setDate_end(String date_end) {
		this.date_end = date_end;
	}
	public String getWorkplace() {
		return workplace;
	}
	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}
	public String getPosition_held() {
		return position_held;
	}
	public void setPosition_held(String position_held) {
		this.position_held = position_held;
	}
	public Practitioner getPractitioner() {
		return practitioner;
	}
	public void setPractitioner(Practitioner practitioner) {
		this.practitioner = practitioner;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
	

}
