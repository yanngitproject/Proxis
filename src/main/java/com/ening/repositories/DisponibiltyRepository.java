package com.ening.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ening.entities.Consultation;

public interface DisponibiltyRepository extends JpaRepository<Consultation, Long> {

}
