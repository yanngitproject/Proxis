package com.proxis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proxis.entities.Studies;

public interface StudiesRepository extends JpaRepository<Studies, Long>  {

}
