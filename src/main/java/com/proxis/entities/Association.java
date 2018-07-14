package com.proxis.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="associations")
public class Association {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id_association;
	private String date_bigin;
	private String date_end;
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "id_practitioner")
	private Practitioner practitioner;

	public String getId_association() {
		return id_association;
	}

	public void setId_association(String id_association) {
		this.id_association = id_association;
	}

	public String getDate_bigin() {
		return date_bigin;
	}

	public void setDate_bigin(String date_bigin) {
		this.date_bigin = date_bigin;
	}

	public String getDate_end() {
		return date_end;
	}

	public void setDate_end(String date_end) {
		this.date_end = date_end;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Practitioner getPractitioner() {
		return practitioner;
	}

	public void setPractitioner(Practitioner practitioner) {
		this.practitioner = practitioner;
	}
	
	
	
	

}
