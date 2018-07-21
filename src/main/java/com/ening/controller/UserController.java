package com.ening.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ening.entities.Experience;
import com.ening.entities.Patient;
import com.ening.entities.Practitioner;
import com.ening.entities.Studies;
import com.ening.entities.User;
import com.ening.providers.Constants;
import com.ening.providers.Utilities;
import com.ening.repositories.PatientRepository;
import com.ening.repositories.PractitionerRepository;
import com.ening.repositories.UserRepository;

@Controller
public class UserController {

	static final String dashboardTemplatesPrefix = "user";
	static final String dashboardPatientTemplate = dashboardTemplatesPrefix + "/patient";
	static final String dashboardPracticeTemplate = dashboardTemplatesPrefix + "/practitioner";
	static final String patient = "PATIENT";
	private static String UPLOADED_FOLDER = "C:\\Users\\Jeanyannick\\Pictures";
	final String date = Utilities.todayDate();

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PractitionerRepository practitionerRepository;

	@RequestMapping("/user")
	public String getProfilPractitioner(RedirectAttributes rattrs) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null && auth.isAuthenticated()
				&& !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {

			User u = userRepository.findUserByUserName(auth.getName());
			u.setUserConnectionNumber(u.getUserConnectionNumber() + 1);
			userRepository.save(u);

			long id = u.getUserId();
			if (u.getUserType().equals(patient)) {

				rattrs.addAttribute("id", id);
				return "redirect:/patient";

			} else {
				rattrs.addAttribute("id", id);
				return "redirect:/practitioner";
			}

		}

		return "redirect:/";

	}

	@GetMapping("/patient")
	public String GetPatient(Model model, @RequestParam("id") long id) {
		Patient patient = patientRepository.getOne(id);
		model.addAttribute("patient", patient);
		model.addAttribute("doualaCities", Constants.doualaCities);
		model.addAttribute("yaoundeCities", Constants.yaoundeCities);
		model.addAttribute("genders", Constants.Genders);
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

	@GetMapping("/practitioner")
	public String GetPractitioner(Model model, @RequestParam("id") long id) {
		Practitioner practitioner = practitionerRepository.getOne(id);

		model.addAttribute("practitioner", practitioner);
		model.addAttribute("doualaCities", Constants.doualaCities);
		model.addAttribute("yaoundeCities", Constants.yaoundeCities);
		model.addAttribute("genders", Constants.Genders);
		model.addAttribute("bloods", Constants.bloodsGroup);
		model.addAttribute("grades", Constants.grades);
		model.addAttribute("specialities", Constants.specialities);
		model.addAttribute("studie0", new Studies());
		model.addAttribute("studie1", new Studies());
		model.addAttribute("studie2", new Studies());
		model.addAttribute("experience0", new Experience());
		model.addAttribute("experience1", new Experience());
		model.addAttribute("experience2", new Experience());

		return dashboardPracticeTemplate;

	}

	@PostMapping("/UpdatePractitioner")
	public String PostPractitioner(@ModelAttribute("practitioner") Practitioner practitioner,
			@ModelAttribute("studie0") Studies studie0, @ModelAttribute("studie1") Studies studie1,
			@ModelAttribute("studie2") Studies studie2, @ModelAttribute("experience0") Experience experience0,
			@ModelAttribute("experience1") Experience experience1,
			@ModelAttribute("experience2") Experience experience2,

			RedirectAttributes rtts) {

		final List<Experience> experiences = new ArrayList<>();
		experiences.add(experience0);
		experiences.add(experience1);
		experiences.add(experience2);

		
		final List<Studies> studies = new ArrayList<>();
		studies.add(studie0);
		studies.add(studie1);
		studies.add(studie2);

		practitioner.setUserTown();
		practitioner.setUpdatedAt(date);
		practitioner.setUserName(Utilities.buildUserName(practitioner));
		practitioner.setExperiences(experiences);
		practitioner.setStudies(studies);
		
		
		return dashboardPracticeTemplate;

	}

}
