package com.proxis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proxis.entities.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

}
