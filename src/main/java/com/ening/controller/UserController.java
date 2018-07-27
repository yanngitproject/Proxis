package com.ening.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ening.entities.User;
import com.ening.providers.Utilities;
import com.ening.repositories.UserRepository;

@Controller
public class UserController {

	static final String dashboardTemplatesPrefix = "user";
	static final String patient = "PATIENT";
	final String date = Utilities.todayDate();



	@Autowired
	UserRepository userRepository;


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

	
	
}
