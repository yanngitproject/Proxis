package com.ening.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ening.entities.Patient;
import com.ening.entities.Practitioner;
import com.ening.entities.Role;
import com.ening.entities.User;
import com.ening.providers.Constants;
import com.ening.providers.Utilities;
import com.ening.repositories.PatientRepository;
import com.ening.repositories.PractitionerRepository;
import com.ening.repositories.RoleRepository;
import com.ening.repositories.UserRepository;
import com.ening.services.EmailService;
import com.ening.services.UserService;

/**
 * @author Jeanyannick
 *
 */
@Controller
public class SignUpController {


	@Autowired
	UserRepository uRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	PractitionerRepository practitionerRepository;

	@Autowired
	public EmailService emailService;

	static final String userTemplatesPrefix = "user";
	static final String userIndexTemplate = userTemplatesPrefix + "/list";
	static final String userEditTemplate = userTemplatesPrefix + "/update";
	static final String userRegisterTemplate = userTemplatesPrefix + "/create";
	static final String userConfirmTemplate = userTemplatesPrefix + "/confirm";

	@RequestMapping("")
	public String users(Model model) {
		List<User> users = uRepository.findAll();
		model.addAttribute("usersList", users);
		return userIndexTemplate;
	}

	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String registerPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("genders", Constants.Genders);
		return userRegisterTemplate;
	}

	@PostMapping("/register")
	public ModelAndView registration(ModelAndView modelAndView, @ModelAttribute("user") @Valid User u,
			BindingResult result, HttpServletRequest request) throws MessagingException, IOException {

		User userExists = userService.findUserByEmail(u.getUserEmail());

		if (userExists != null) {
			modelAndView.addObject("alreadyRegiste	redMessage",
					"Oops! There is already a user registered with the email provided.");
			modelAndView.setViewName(userRegisterTemplate);
			result.reject("email");
		}

		if (result.hasErrors()) {
			modelAndView.setViewName(userRegisterTemplate);
		} else {
			
			String confirmationToken = UUID.randomUUID().toString();
            String userName = Utilities.buildUserName(u);
            //check if exist is necessary
            String date = Utilities.todayDate();
            if(u.getUserType().equals("Patient")){
            	
    			Patient patient = new Patient();
    			patient.setConfirmationToken(confirmationToken);
    			patient.setUserEmail(u.getUserEmail());
    			patient.setUserLastName(u.getUserLastName());
    			patient.setUserFirstName(u.getUserFirstName());
    			patient.setUserSex(u.getUserSex());
    			patient.setUserPhoneNumber(u.getUserPhoneNumber());
    			patient.setUserType(u.getUserType().toUpperCase());
    			patient.setUserName(userName);
    			patient.setEnable(false);
    			patient.setCreatedAt(date);
    			patient.setUpdatedAt(date);
    			patient.setUserConnectionNumber(0);
    			patientRepository.save(patient);
    			
    		}else{
    			
    			Practitioner practitioner  = new Practitioner();
    			practitioner.setConfirmationToken(confirmationToken);
    			practitioner.setUserEmail(u.getUserEmail());
    			practitioner.setUserLastName(u.getUserLastName());
    			practitioner.setUserFirstName(u.getUserFirstName());
    			practitioner.setUserSex(u.getUserSex());
    			practitioner.setUserPhoneNumber(u.getUserPhoneNumber());
    			practitioner.setUserType(u.getUserType().toUpperCase());
    			practitioner.setUserName(userName);
    			practitioner.setEnable(false);
    			practitioner.setCreatedAt(date);
    			practitioner.setUpdatedAt(date);
    			practitioner.setUserConnectionNumber(0);
    			practitionerRepository.save(practitioner);
    				
    		}


			String appUrl = request.getScheme() + "://" + request.getServerName()+":"+request.getServerPort();

			SimpleMailMessage registrationEmail = new SimpleMailMessage();
			registrationEmail.setTo(u.getUserEmail());
			registrationEmail.setSubject("Proxis Registration Confirmation");
			registrationEmail.setText("Hello, your USERNAME is "+u.getUserName()+"\nTo confirm your e-mail address, please click the link below:\n" + appUrl
					+ "/confirm?token=" + confirmationToken);
			registrationEmail.setFrom("cawapps@gmail.com");
			emailService.sendEmail(registrationEmail);
			modelAndView.addObject("confirmationMessage", "A confirmation e-mail has been sent to " + u.getUserEmail());
			modelAndView.setViewName(userRegisterTemplate);
		}

		return modelAndView;
	}

	@GetMapping(value = "/confirm")
	public String showConfirmationPage(Model model, @RequestParam("token") String token) {

		User user = userService.findByConfirmationToken(token);

		if (user == null) {
			model.addAttribute("invalidToken", "Oops!  This is an invalid confirmation link.");
		} else {
			model.addAttribute("confirmationToken", user.getConfirmationToken());
		}

		return userConfirmTemplate;
	}

	@PostMapping(value = "/confirm")
	public ModelAndView processConfirmationForm(ModelAndView modelAndView, BindingResult bindingResult,
			@RequestParam Map<String, String> requestParams, RedirectAttributes redir) {

		modelAndView.setViewName(userConfirmTemplate);

//		Zxcvbn passwordCheck = new Zxcvbn();
//
//		Strength strength = passwordCheck.measure((String) requestParams.get("password"));
//
//		if (strength.getScore() < 3) {
//
//			bindingResult.reject("password");
//
//			redir.addFlashAttribute("errorMessage", "Your password is too weak.  Choose a stronger one.");
//
//			modelAndView.setViewName("redirect:/user/confirm?token=" + requestParams.get("token"));
//
//			return modelAndView;
//		}

		User user = userService.findByConfirmationToken((String) requestParams.get("token"));

		user.setUserPassword(new BCryptPasswordEncoder().encode((String) requestParams.get("password")));

		user.setEnable(true);	

		userService.saveUser(user);	

		modelAndView.addObject("successMessage", user.getUserName() + ", your password has been set!");
		return modelAndView;
	}

	@RequestMapping(value = "/delete")
	public String deleteUser(@RequestParam(name = "userId") long userId) {

		uRepository.deleteById(userId);

		return "redirect:/users";

	}

	@RequestMapping(value = "/edit")
	public String editUser(@RequestParam(name = "userId") long userId, Model model) {

		User user = uRepository.getOne(userId);
		Iterable<Role> roles = roleRepository.findAll();

		model.addAttribute("user", user);
		model.addAttribute("prevUser", user);
		model.addAttribute("rolesList", roles);

		return userEditTemplate;
	}

	@RequestMapping(value = "/update")
	public String updateUser(@Valid User user) {

		uRepository.save(user);

		return "redirect:/users";
	}

}
