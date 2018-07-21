package com.ening.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ening.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByCode(String code);
}