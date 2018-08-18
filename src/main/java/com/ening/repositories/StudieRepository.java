package com.ening.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ening.entities.Studie;

public interface StudieRepository extends JpaRepository<Studie, Long>  {
	
	List<Studie> findBySpeciality(String speciality);

}
