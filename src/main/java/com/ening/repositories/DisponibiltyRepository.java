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
    @Query(value="delete from disponibilities  where id_practitioner = ?1 and id_event = ?2",nativeQuery = true)
	void DeleteWithPractitionerAndId_event(long id_practitioner ,long id_event);

}
