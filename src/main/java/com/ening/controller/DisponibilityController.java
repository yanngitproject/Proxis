package com.ening.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ening.entities.Practitioner;
import com.ening.providers.Constants;
import com.ening.repositories.DisponibiltyRepository;
import com.ening.repositories.PractitionerRepository;

@Controller
public class DisponibilityController {

	@Autowired
	PractitionerRepository practitionerRepository;

	@Autowired
	DisponibiltyRepository disponibilityRepository;

	@RequestMapping("/disponibilities")
	public String Getdispo(Model model, @RequestParam("id") long id) {

		Practitioner practitioner = practitionerRepository.getOne(id);

		System.out.println(practitioner.getDisponibilities().toString());
		model.addAttribute("practitioner", practitioner);
		model.addAttribute("doualaCities", Constants.doualaCities);
		model.addAttribute("yaoundeCities", Constants.yaoundeCities);
		model.addAttribute("categories", Constants.categories);
		model.addAttribute("slots", Constants.slots);

		return "disponibilities";

	}
}
