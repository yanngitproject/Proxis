package com.ening.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="disponibilities")
public class Disponibility {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_disponibility;
	private String id_event;
	private String startDate;
	private String endDate;
	@ElementCollection
	@Column(nullable= true)
	private List<String> slots;
	@ElementCollection
	@Column(nullable= true)
	private List<String> categories; // call or physical
	private String status; // free,occuped
	@ElementCollection
	@Column(nullable= true)
    private List<String> cities;	// city
    @JsonIgnore
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


	public String getId_event() {
		return id_event;
	}


	public void setId_event(String id_event) {
		this.id_event = id_event;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public List<String> getCategories() {
		return categories;
	}


	public void setCategories(List<String> categories) {
		this.categories = categories;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public List<String> getCities() {
		return cities;
	}


	public void setCities(List<String> cities) {
		this.cities = cities;
	}


	public Practitioner getPractitioner() {
		return practitioner;
	}


	public void setPractitioner(Practitioner practitioner) {
		this.practitioner = practitioner;
	}


	public List<String> getSlots() {
		return slots;
	}


	public void setSlots(List<String> slots) {
		this.slots = slots;
	}


	@Override
	public String toString() {
		return "Disponibility [id_disponibility=" + id_disponibility + ", id_event=" + id_event + ", startDate="
				+ startDate + ", endDate=" + endDate + ", slots=" + slots + ", categories=" + categories + ", status="
				+ status + ", cities=" + cities + ", practitioner=" + practitioner + "]";
	}
	

	
	

	
	
	
	
}
