package com.proxis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proxis.entities.Practitioner;

public interface PractitionerRepository extends JpaRepository<Practitioner, Long> {

}
