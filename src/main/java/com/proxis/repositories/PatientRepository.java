package com.proxis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proxis.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	
	Patient findByUserName(String userName);

}
