package com.ening.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ening.entities.Practitioner;

public interface PractitionerRepository extends JpaRepository<Practitioner, Long> {

}
