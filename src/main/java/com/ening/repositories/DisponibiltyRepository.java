package com.ening.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ening.entities.Disponibility;
import com.ening.entities.Practitioner;

public interface DisponibiltyRepository extends JpaRepository<Disponibility	, Long> {
	
    @Modifying
    @Transactional
    @Query("delete from Disponibility d where d.practitioner = ?1")
	void DeleteByPractitioner(Practitioner practitioner);

}
