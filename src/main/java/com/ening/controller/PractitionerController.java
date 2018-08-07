package com.ening.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ening.entities.Experience;
import com.ening.entities.Practitioner;
import com.ening.entities.Studies;
import com.ening.providers.Constants;
import com.ening.providers.Utilities;
import com.ening.repositories.PractitionerRepository;
import com.ening.repositories.UserRepository;

@Controller
public class PractitionerController {
	
	static final String dashboardTemplatesPrefix = "user";
	static final String dashboardPracticeTemplate = dashboardTemplatesPrefix + "/practitioner";
	final String date = Utilities.todayDate();

	
	@Autowired
	UserRepository userRepository;

	@Autowired
	PractitionerRepository practitionerRepository;
	
	@GetMapping("/practitioner")
	public String GetPractitioner(Model model, @RequestParam("id") long id) {
		Practitioner practitioner = practitionerRepository.getOne(id);

		model.addAttribute("practitioner", practitioner);
		model.addAttribute("doualaCities", Constants.doualaCities);
		model.addAttribute("yaoundeCities", Constants.yaoundeCities);
		model.addAttribute("genders", Constants.genders);
		model.addAttribute("bloods", Constants.bloodsGroup);
		model.addAttribute("experience",new Experience());
		model.addAttribute("studie",new Studies());
		model.addAttribute("specialities", Constants.specialities);
		model.addAttribute("grades", Constants.grades);
	
		return dashboardPracticeTemplate;

	}

	@PostMapping("/UpdatePractitioner")
	public String PostPractitioner(@ModelAttribute("practitioner") Practitioner practitioner,RedirectAttributes rtts) {
		practitioner.setUserTown();
		practitioner.setUpdatedAt(date);
		practitioner.setUserName(Utilities.buildUserName(practitioner));
		practitionerRepository.save(practitioner);
		rtts.addAttribute("id", practitioner.getUserId());
		return "redirect:/practitioner";

	}


}
