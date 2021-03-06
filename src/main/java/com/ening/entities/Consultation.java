package com.ening.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="consultations")
public class Consultation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_consultation;
	private String categorie;
	private String date;
	private String begin_hour;
	private String end_hour;
	private String city;
	private int status;
	private String declared_symptoms;
	private String details;
	@JsonIgnore
	@OneToMany(mappedBy = "consultation", cascade = CascadeType.ALL)
	private List<Prescription> prescriptions;
	
	@ManyToOne
    @JoinColumn(name = "id_practitioner")
    private Practitioner practitioner;
	
    @ManyToOne
    @JoinColumn(name = "id_patient")
    private Patient patient; 
    
    private int practice_note;
	public long getId_consultation() {
		return id_consultation;
	}
	public void setId_consultation(long id_consultation) {
		this.id_consultation = id_consultation;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getBegin_hour() {
		return begin_hour;
	}
	public void setBegin_hour(String begin_hour) {
		this.begin_hour = begin_hour;
	}
	public String getEnd_hour() {
		return end_hour;
	}
	public void setEnd_hour(String end_hour) {
		this.end_hour = end_hour;
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
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public int getPractice_note() {
		return practice_note;
	}
	public void setPractice_note(int practice_note) {
		this.practice_note = practice_note;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDeclared_symptoms() {
		return declared_symptoms;
	}
	public void setDeclared_symptoms(String declared_symptoms) {
		this.declared_symptoms = declared_symptoms;
	}
	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}
	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}
    
    
	
	

}
