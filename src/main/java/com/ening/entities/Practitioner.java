package com.ening.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "practitioners")
public class Practitioner extends User {

	private String idOnmc;
	private String orangeNumber;
	private String mtnNumber;
	@JsonIgnore
	@OneToMany(mappedBy = "practitioner", cascade = CascadeType.ALL)
	private List<Studie> studies;
	@JsonIgnore
	@OneToMany(mappedBy = "practitioner", cascade = CascadeType.ALL)
	private List<Experience> experiences;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "practitioner_payment", joinColumns = {
			@JoinColumn(name = "id_practitioner", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_payment", nullable = false, updatable = false) })
	private List<Payment> payments;
	private int experiences_years;
	private double price;
	@JsonIgnore
	@OneToMany(mappedBy = "practitioner", cascade = CascadeType.ALL)
	private List<Disponibility> disponibilities;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "practitioner_patient", joinColumns = {
			@JoinColumn(name = "id_practitioner", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "id_patient", nullable = false, updatable = false) })
	private List<Patient> patients;
	@JsonIgnore
	@OneToMany(mappedBy = "practitioner", cascade = CascadeType.ALL)
	private List<Consultation> consultations;

	public List<Studie> getStudies() {
		return studies;
	}

	public void setStudies(List<Studie> studies) {
		this.studies = studies;
	}

	public int getExperiences_years() {
		return experiences_years;
	}

	public void setExperiences_years(int experiences_years) {
		this.experiences_years = experiences_years;
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

	public void addExperience(Experience experience) {

		if (!experiences.contains(experience)) {

			experiences.add(experience);
		}

	}

	public void removeExperience(Experience experience) {

		if (experiences.contains(experience)) {
			experiences.remove(experience);
		}

	}

	public void addStudie(Studie studie) {

		if (!studies.contains(studie)) {

			studies.add(studie);
		}

	}

	public void removeStudie(Studie studie) {

		if (studies.contains(studie)) {

			studies.remove(studie);
		}

	}


	public String getOrangeNumber() {
		return orangeNumber;
	}

	public void setOrangeNumber(String orangeNumber) {
		this.orangeNumber = orangeNumber;
	}

	public String getMtnNumber() {
		return mtnNumber;
	}

	public void setMtnNumber(String mtnNumber) {
		this.mtnNumber = mtnNumber;
	}

	public String getIdOnmc() {
		return idOnmc;
	}

	public void setIdOnmc(String idOnmc) {
		this.idOnmc = idOnmc;
	}

	
}
