package com.proxis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proxis.entities.Experience;

public interface AssociationRepository extends JpaRepository<Experience, Long> {

}
