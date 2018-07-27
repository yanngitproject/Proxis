package com.ening.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ening.entities.Practitioner;
import com.ening.repositories.PractitionerRepository;
import com.ening.repositories.UserRepository;

@Controller
public class ConsultationsController {


	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PractitionerRepository practitionerRepository;



	@RequestMapping("/consultations")
	public String Get(Model model, @RequestParam("id") long id) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Practitioner practitioner = practitionerRepository.getOne(id);
		
		model.addAttribute("practitioner", practitioner);
		
		return "consultations";

	}
	


	
	
	

}
