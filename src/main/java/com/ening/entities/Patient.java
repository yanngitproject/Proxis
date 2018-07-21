package com.ening.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="patients")
public class Patient extends User{
	

	private String bloodGroup;
	private String antecedents;
	private String allergies;
	private String symptomes;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "patients")
	private List<Practitioner> practitioners;
	
	@OneToMany(mappedBy="patient", cascade=CascadeType.ALL)
	private List<Consultation> consultations;
	
	
	public Patient() {
		super();
	}
	
	public String getSymptomes() {
		return symptomes;
	}
	public void setSymptomes(String symptomes) {
		this.symptomes = symptomes;
	}
	public List<Practitioner> getPractitioners() {
		return practitioners;
	}
	public void setPractitioners(List<Practitioner> practitioners) {
		this.practitioners = practitioners;
	}
	public List<Consultation> getConsultations() {
		return consultations;
	}
	public void setConsultations(List<Consultation> consultations) {
		this.consultations = consultations;
	}
	
	public String getAntecedents() {
		return antecedents;
	}

	public void setAntecedents(String antecedents) {
		this.antecedents = antecedents;
	}



	public String getAllergies() {
		return allergies;
	}



	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}



	public String getBloodGroup() {
		return bloodGroup;
	}



	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}



	@Override
	public String toString() {
		return "Patient [bloodGroup=" + bloodGroup + ", antecedents=" + antecedents + ", allergies=" + allergies
				+ ", symptomes=" + symptomes + ", practitioners=" + practitioners + ", consultations=" + consultations
				+ "]";
	}
	
	
	
	
	
}
