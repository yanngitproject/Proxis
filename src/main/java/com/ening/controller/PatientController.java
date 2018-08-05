package com.ening.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ening.entities.Patient;
import com.ening.providers.Constants;
import com.ening.providers.Utilities;
import com.ening.repositories.PatientRepository;

@Controller
public class PatientController {
	
	static final String dashboardTemplatesPrefix = "user";
	static final String dashboardPatientTemplate = dashboardTemplatesPrefix + "/patient";
	final String date = Utilities.todayDate();

	@Autowired
	PatientRepository patientRepository;
	
	@GetMapping("/patient")
	public String GetPatient(Model model, @RequestParam("id") long id) {
		Patient patient = patientRepository.getOne(id);
		model.addAttribute("patient", patient);
		model.addAttribute("doualaCities", Constants.doualaCities);
		model.addAttribute("yaoundeCities", Constants.yaoundeCities);
		model.addAttribute("genders", Constants.genders);
		model.addAttribute("bloods", Constants.bloodsGroup);

		return dashboardPatientTemplate;

	}

	@PostMapping("/UpdatePatient")
	public String PostPatient(@ModelAttribute("patient") Patient patient, RedirectAttributes rtts) {
		patient.setUserTown();
		patient.setUpdatedAt(date);
		patient.setUserName(Utilities.buildUserName(patient));

		patientRepository.save(patient);
		rtts.addAttribute("id", patient.getUserId());
		return "redirect:/patient";

	}


}
