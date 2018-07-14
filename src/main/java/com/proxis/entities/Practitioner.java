package com.proxis.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "practitioners")
public class Practitioner extends User {

	private String presentation;
	private String current_activity;

	@OneToMany(mappedBy = "practitioner", cascade = CascadeType.ALL)
	private List<Studies> studies;
	@OneToMany(mappedBy = "practitioner", cascade = CascadeType.ALL)
	private List<Experience> experiences;
	@OneToMany(mappedBy = "practitioner", cascade = CascadeType.ALL)
	private List<Association> associations;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "practitioner_payment", joinColumns = {
			@JoinColumn(name = "id_practitioner", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_payment", nullable = false, updatable = false) })
	private List<Payment> payments;
	private int experiences_years;
	private double price;


	@OneToMany(mappedBy = "practitioner", cascade = CascadeType.ALL)
	private List<Disponibility> disponibilities;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "practitioner_patient", joinColumns = {
			@JoinColumn(name = "id_practitioner", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_patient", nullable = false, updatable = false) })
	private List<Patient> patients;

	@OneToMany(mappedBy = "practitioner", cascade = CascadeType.ALL)
	private List<Consultation> consultations;

	
	public List<Studies> getStudies() {
		return studies;
	}

	public void setStudies(List<Studies> studies) {
		this.studies = studies;
	}

	public int getExperiences_years() {
		return experiences_years;
	}

	public void setExperiences_years(int experiences_years) {
		this.experiences_years = experiences_years;
	}

	public String getCurrent_activity() {
		return current_activity;
	}

	public void setCurrent_activity(String current_activity) {
		this.current_activity = current_activity;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}


	public List<Disponibility> getDisponibilities() {
		return disponibilities;
	}

	public void setDisponibilities(List<Disponibility> disponibilities) {
		this.disponibilities = disponibilities;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public List<Consultation> getConsultations() {
		return consultations;
	}

	public void setConsultations(List<Consultation> consultations) {
		this.consultations = consultations;
	}

	

	public List<Experience> getExperiences() {
		return experiences;
	}

	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	public List<Association> getAssociations() {
		return associations;
	}

	public void setAssociations(List<Association> associations) {
		this.associations = associations;
	}


}
