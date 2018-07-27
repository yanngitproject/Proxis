package com.ening.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ening.entities.Disponibility;
import com.ening.entities.Practitioner;
import com.ening.providers.Utilities;
import com.ening.repositories.DisponibiltyRepository;
import com.ening.repositories.PractitionerRepository;

@Controller
public class DisponibilityController {

	@Autowired
	PractitionerRepository practitionerRepository;

	@Autowired
	DisponibiltyRepository disponibilityRepository;

	@RequestMapping("/disponibilities")
	public String Getdispo(Model model, @RequestParam("id") long id) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Practitioner practitioner = practitionerRepository.getOne(id);

		model.addAttribute("id", id);
		model.addAttribute("disponibilities", practitioner.getDisponibilities());
		model.addAttribute("name", auth.getName());

		return "disponibilities";

	}

	@PostMapping("/addDisponibility")
	public String Postdispo(@RequestParam("id") long id, @RequestParam("dateBegin") String dateBegin,
			@RequestParam("dateEnd") String dateEnd, RedirectAttributes rtts) throws ParseException {

		Practitioner practitioner = practitionerRepository.getOne(id);

		Disponibility dispo = new Disponibility();
		dispo.setBeginDate(dateBegin);
		dispo.setEndDate(dateBegin);
		dispo.setPractitioner(practitioner);

		disponibilityRepository.save(dispo);

		rtts.addAttribute("id", id);
		return "redirect:/disponibilities";

	}

	@RequestMapping("/delete_disponibilities")
	public String remAlldispo(@RequestParam("id") long id, RedirectAttributes rtts) throws ParseException {

		Practitioner practitioner = practitionerRepository.getOne(id);

		disponibilityRepository.DeleteByPractitioner(practitioner);

		rtts.addAttribute("id", id);
		return "redirect:/disponibilities";

	}

	@RequestMapping("/delete_disponibility")
	public String remdispo(@RequestParam("idDispo") long dispoId, @RequestParam("id") long id, RedirectAttributes rtts)
			throws ParseException {

		disponibilityRepository.deleteById(dispoId);

		rtts.addAttribute("id", id);
		return "redirect:/disponibilities";

	}

}
