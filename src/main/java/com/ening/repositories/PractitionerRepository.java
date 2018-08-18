package com.ening.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ening.entities.Practitioner;
import com.ening.entities.Studie;

public interface PractitionerRepository extends JpaRepository<Practitioner, Long> {

	

}
