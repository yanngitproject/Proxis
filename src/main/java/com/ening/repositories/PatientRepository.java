package com.ening.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ening.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	
	Patient findByUserName(String userName);

}
