package com.ening.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prescriptions")
public class Prescription {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id_prescription;
	private String doctor_name;
	private String date;
	private String medication;
	private String dosage;
	private String analysis;
	private String advice;

	@ManyToOne
	@JoinColumn(name = "id_consultation")
	private Consultation consultation;

	public Prescription() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId_prescription() {
		return id_prescription;
	}

	public void setId_prescription(long id_prescription) {
		this.id_prescription = id_prescription;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMedication() {
		return medication;
	}

	public void setMedication(String medication) {
		this.medication = medication;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public Consultation getConsultation() {
		return consultation;
	}

	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}

	@Override
	public String toString() {
		return "Prescription [id_prescription=" + id_prescription + ", doctor_name=" + doctor_name + ", date=" + date
				+ ", medication=" + medication + ", dosage=" + dosage + ", analysis=" + analysis + ", advice=" + advice
				+ ", consultation=" + consultation + "]";
	}

	
	
}
