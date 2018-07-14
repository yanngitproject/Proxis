package com.proxis.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proxis.entities.Role;
import com.proxis.entities.User;
import com.proxis.providers.Utilities;
import com.proxis.repositories.RoleRepository;
import com.proxis.repositories.UserRepository;
import com.proxis.services.RoleService;

@Controller
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleService roleManager;

	static final String roleTemplatesPrefix = "role";
	static final String roleIndexTemplate = roleTemplatesPrefix + "/index";
	static final String roleEditTemplate = roleTemplatesPrefix + "/update";
	static final String roleCreateTemplate = roleTemplatesPrefix + "/create";

	@GetMapping("")
	public String getRolesAction(Model model) {

		Iterable<Role> roles = roleRepository.findAll();

		model.addAttribute("rolesList", roles);

		return roleIndexTemplate;
	}

	@GetMapping("/create")
	public String createRoleAction(Model model) {
		
		Role role = new Role();
		Iterable<User> usersList = userRepository.findAll();
		

		model.addAttribute("role", role);
		model.addAttribute("usersList", usersList);
		
		return roleCreateTemplate;
	}

	@PostMapping("/create")
	public String createRolePostAction(@Valid Role role) {

		do {

			role.setCode(
				Utilities.buildCode("ROLE",10)
			);
		} while (roleManager.roleExists(role));


        roleRepository.save(role);
		
        return "redirect:/roles";
	}

	@GetMapping("/edit/{id}")
	public String editRoleAction(@PathVariable(value = "id") Long roleId, Model model) {
		
		Role role = roleRepository.getOne(roleId);

		if (role == null) {

	        return "";// to-do: setup a not found fallback
	    }

	    Iterable<User> usersList = userRepository.findAll();

		model.addAttribute("role", role);
		model.addAttribute("usersList", usersList);

		return roleEditTemplate;
	}

	@PostMapping("/update/{id}")
    public String updateRoleAction(@PathVariable(value = "id") Long roleId, @Valid Role roleData) {


        Role role = roleRepository.getOne(roleId);

		if (role == null) {

	        return "";// to-do: setup a not found fallback
	    }

	    role.setLabel(roleData.getLabel());
        
        roleRepository.save(role);

        return "redirect:/roles";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoleAction(@PathVariable(value = "id") Long roleId) {
    // to-do: make this action a post


        Role role = roleRepository.getOne(roleId);

		if (role == null) {

	        return "";// to-do: setup a not found fallback
	    }

        roleRepository.delete(role);

        return "redirect:/roles";
    }
}
