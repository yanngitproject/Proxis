
package com.proxis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proxis.entities.Role;
import com.proxis.repositories.RoleRepository;




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
