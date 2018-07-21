
package com.ening.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ening.entities.Role;
import com.ening.repositories.RoleRepository;




@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	public boolean roleExists (String code) {

		Role role = roleRepository.findByCode(code);

		return role!=null;
	}

	public boolean roleExists (Role role) {

		return this.roleExists(role.getCode());
	}

	
}
