package com.proxis.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

	static final String dashboardTemplate = "dashboard";
	static final String userManagementTemplate = "user/manage";

	@RequestMapping("/")
	public String welcome() {

		return "redirect:/welcome";
	}
	
	@RequestMapping(value = "/welcome")
	public String welcome(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = null;

		if (auth != null && auth.isAuthenticated()
				&& !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {

			name = auth.getName();
		}
		model.addAttribute("authenticated", name);

		return "welcome";
	}

}
