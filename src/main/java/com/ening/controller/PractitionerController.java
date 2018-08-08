package com.ening.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ening.entities.Experience;
import com.ening.entities.Practitioner;
import com.ening.entities.Studie;
import com.ening.providers.Constants;
import com.ening.providers.Utilities;
import com.ening.repositories.ExperienceRepository;
import com.ening.repositories.PractitionerRepository;
import com.ening.repositories.StudieRepository;

@Controller
public class PractitionerController {

	static final String dashboardTemplatesPrefix = "user";
	static final String dashboardPracticeTemplate = dashboardTemplatesPrefix + "/practitioner";
	final String date = Utilities.todayDate();

	@Autowired
	StudieRepository studieRepository;

	@Autowired
	PractitionerRepository practitionerRepository;
	
	@Autowired
	ExperienceRepository experienceRepository;

	@GetMapping("/practitioner")
	public String GetPractitioner(Model model, @RequestParam("id") long id) {
		Practitioner practitioner = practitionerRepository.getOne(id);

		model.addAttribute("practitioner", practitioner);
		model.addAttribute("doualaCities", Constants.doualaCities);
		model.addAttribute("yaoundeCities", Constants.yaoundeCities);
		model.addAttribute("genders", Constants.genders);
		model.addAttribute("bloods", Constants.bloodsGroup);
		model.addAttribute("experience", new Experience());
		model.addAttribute("studie", new Studie());
		model.addAttribute("specialities", Constants.specialities);
		model.addAttribute("grades", Constants.grades);

		return dashboardPracticeTemplate;

	}

	@RequestMapping(value = "/add_studie")
	public String addStudie(@RequestParam("id") long id, @ModelAttribute("studie") Studie studie,
			RedirectAttributes rtts) {
		
		Practitioner practitioner = practitionerRepository.getOne(id);
        studie.setPractitioner(practitioner);
        
      
        studieRepository.save(studie);

		
		rtts.addAttribute("id", id);
		return "redirect:/practitioner";
	}
	
	@RequestMapping(value = "/delete_studie")
	public String deleteStudie(@RequestParam(value = "id") long id,RedirectAttributes rtts) {

		Studie studie = studieRepository.getOne(id);
		studieRepository.delete(studie);
		rtts.addAttribute("id", studie.getPractitioner().getUserId());
		return "redirect:/practitioner";

	}
	
	
	@RequestMapping(value = "/add_experience")
	public String addexperience(@RequestParam("id") long id, @ModelAttribute("experience") Experience exp,
			RedirectAttributes rtts) {
		
		Practitioner practitioner = practitionerRepository.getOne(id);
        exp.setPractitioner(practitioner);
        
      
        experienceRepository.save(exp);

		
		rtts.addAttribute("id", id);
		return "redirect:/practitioner";
	}
	
	@RequestMapping(value = "/delete_experience")
	public String deletexperience(@RequestParam(value = "id") long id,RedirectAttributes rtts) {

		Experience exp = experienceRepository.getOne(id);
		experienceRepository.delete(exp);
		rtts.addAttribute("id", exp.getPractitioner().getUserId());
		return "redirect:/practitioner";
		

	}
	
}
