package com.ening.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ening.entities.User;
import com.ening.providers.Utilities;
import com.ening.services.EmailService;
import com.ening.services.UserService;


/**
 * @author Jeanyannick
 *
 */
@Controller
public class PasswordForgotController {

	@Autowired
	private EmailService emailService;

	@Autowired
	private UserService userService;

	static final String userTemplatesPrefix = "user";
	static final String userResetTemplate = userTemplatesPrefix + "/reset";
	static final String userForgotTemplate = userTemplatesPrefix + "/forgot";

	@GetMapping(value = "/forgot")
	public String forget(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null && auth.isAuthenticated()
				&& !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {

			return "redirect:/";
		}

		return userForgotTemplate;
	}

	@PostMapping(value = "/forgot")
	public String processForgotPasswordForm(Model model, @RequestParam("email") String userEmail,
			HttpServletRequest request) {

		User optional = userService.findUserByEmail(userEmail);

		if (optional == null) {
			model.addAttribute("errorMessage", "We didn't find an account for that e-mail address.");
		} else {

			optional.setResetToken(UUID.randomUUID().toString());

			userService.saveUser(optional);

			String appUrl = request.getScheme() + "://" + request.getServerName();

			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setFrom("cawapps237@gmail.com");
			passwordResetEmail.setTo(optional.getUserEmail());
			passwordResetEmail.setSubject("Password Reset Request");
			passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl + "/reset?token="
					+ optional.getResetToken());

			emailService.sendEmail(passwordResetEmail);

			model.addAttribute("successMessage", "A password reset link has been sent to " + userEmail);
		}

		return userForgotTemplate;

	}

	@GetMapping(value = "/reset")
	public String displayResetPasswordPage(Model model, @RequestParam("token") String token) {

		User user = userService.findUserByResetToken(token);

		if (user != null) {

			model.addAttribute("resetToken", token);

		} else {

			model.addAttribute("errorMessage", "Oops! This is an invalid password reset link.");
		}

		return userResetTemplate;
	}

	@PostMapping(value = "/reset")
	public String setNewPassword(@RequestParam("mytoken") String token, @RequestParam("password") String password,
			RedirectAttributes redir, Model model) {

		User resetUser = userService.findUserByResetToken(token);

		if (resetUser != null) {

			resetUser.setUserPassword(new BCryptPasswordEncoder().encode(password));

			resetUser.setResetToken(null);
			resetUser.setUpdatedAt(Utilities.todayDate());

			userService.saveUser(resetUser);

			redir.addFlashAttribute("successMessage", "You have successfully reset your password. You may now login.");

			return "redirect:login";

		} else {
			model.addAttribute("errorMessage", "Oops! This is an invalid password reset link.");
			model.addAttribute("resetPassword");
		}

		return userResetTemplate;
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
		return new ModelAndView("redirect:login");
	}

}
