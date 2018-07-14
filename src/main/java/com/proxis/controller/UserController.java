package com.proxis.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proxis.entities.Patient;
import com.proxis.entities.User;
import com.proxis.providers.Constants;
import com.proxis.providers.Utilities;
import com.proxis.repositories.PatientRepository;
import com.proxis.repositories.PractitionerRepository;
import com.proxis.repositories.UserRepository;

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
		patient.setUserName(Utilities.buildPatientUserName(patient));
		
		
		
		StringJoiner sj = new StringJoiner(" , ");

        for (MultipartFile file : patient.getCni()) {

            if (file.isEmpty()) {
                continue; //next pls
            }

            try {

                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);
                sj.add(file.getOriginalFilename());

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        
        patientRepository.save(patient);		
		rtts.addAttribute("id", patient.getUserId());
		return "redirect:/patient";

	}
	
	

	@RequestMapping("/practitioner")
	public String Practiotiner() {
		return dashboardPracticeTemplate;

	}

}
