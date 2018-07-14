package com.proxis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proxis.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByCode(String code);
}