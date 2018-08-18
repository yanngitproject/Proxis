package com.ening.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ening.entities.Disponibility;
import com.ening.entities.Patient;
import com.ening.providers.Constants;
import com.ening.providers.Utilities;
import com.ening.repositories.DisponibiltyRepository;
import com.ening.repositories.PatientRepository;
import com.ening.services.DisponibilityService;

@Controller
@RequestMapping("/find")
public class SearchController {

	static final String dashboardTemplatesPrefix = "user";
	static final String dashboardPatientTemplate = dashboardTemplatesPrefix + "/patient";
	final String date = Utilities.todayDate();

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	DisponibiltyRepository disponibilityRepository;

	DisponibilityService disponibilityService = new DisponibilityService();

	@GetMapping()
	public String find(Model model, @RequestParam("id") long id,
			@RequestParam(value = "disponibilities", required = false) List<Disponibility> disponibilities) {
		Patient patient = patientRepository.getOne(id);
		model.addAttribute("patient", patient);

		model.addAttribute("doualaCities", Constants.doualaCities);
		model.addAttribute("yaoundeCities", Constants.yaoundeCities);
		model.addAttribute("specialities", Constants.specialities);
		model.addAttribute("categories", Constants.categories);
		model.addAttribute("slots", Constants.slots);
		model.addAttribute("disponibilities",disponibilities);


		return "find";
	}

	@PostMapping()
	public String find(Model model, @RequestParam("id") String id, @RequestParam("city") String city,
			@RequestParam("speciality") String speciality, @RequestParam("categorie") String categorie,
			@RequestParam("slot") String slot, RedirectAttributes rtts) throws ParseException {

		List<Disponibility> disponibilities = disponibilityService.getDisponibilities(disponibilityRepository, city,
				speciality, categorie, slot);
		
		System.out.println(disponibilities.toString());

		rtts.addAttribute("id", id);
		rtts.addAttribute("disponibilities", disponibilities);
		return "redirect:/find";

	}

}
