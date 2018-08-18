package com.ening.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ening.entities.User;
import com.ening.providers.Constants;
import com.ening.repositories.UserRepository;

@Controller
public class LoginController {

	static final String loginTemplatesPrefix = "login";
	static final String loginIndexTemplate = loginTemplatesPrefix + "/index";

	@Autowired
	UserRepository userRepository;



	@RequestMapping("/login")
	public String login(Model model,RedirectAttributes rattrs) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		

		if (auth != null && auth.isAuthenticated()
				&& !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
		     return "redirect:/user";
		}
		
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("genders", Constants.genders);

		return "login/index";

	}

	@RequestMapping("/failLogin")
	public String fail(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null && auth.isAuthenticated()
				&& !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {

			return "redirect:/";
		}

		model.addAttribute("failed", "Authentification failed ");

		return "login/index";
	}

	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return   "redirect:/login?logout";
	}
	
	@RequestMapping("/")
	public String welcome() {

		return "redirect:/login";
	}

	

}
