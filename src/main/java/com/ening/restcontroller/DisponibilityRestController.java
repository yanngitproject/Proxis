package com.ening.restcontroller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ening.entities.Disponibility;
import com.ening.entities.Practitioner;
import com.ening.providers.Utilities;
import com.ening.repositories.DisponibiltyRepository;
import com.ening.repositories.PractitionerRepository;

@org.springframework.web.bind.annotation.RestController
public class DisponibilityRestController {

	@Autowired
	PractitionerRepository practitionerRepository;

	@Autowired
	DisponibiltyRepository disponibilityRepository;

	@RequestMapping(value = "/addDisponibility")
	public String getDisponibilities(@RequestParam(value = "idUser") long idPractitioner,
			@RequestParam(value = "idEvent") String id_event, @RequestParam(value = "start") String startDate,
			@RequestParam(value = "end") String endDate, @RequestParam(value = "slots") List<String> slots,
			@RequestParam(value = "cities") List<String> cities,
			@RequestParam(value = "categories") List<String> categories) throws ParseException {

		Disponibility d = new Disponibility();
		d.setPractitioner(practitionerRepository.getOne(idPractitioner));
		d.setCategories(Utilities.getParsesListFromJSON(categories));
		d.setCities(Utilities.getParsesListFromJSON(cities));
		d.setEndDate(Utilities.getValideDate(endDate));
		d.setStartDate(Utilities.getValideDate(startDate));
		d.setSlots(Utilities.getParsesListFromJSON(slots));
		d.setId_event(id_event);
		d.setStatus("free");
		disponibilityRepository.save(d);

		return "ok";

	}

	@RequestMapping(value = "/removeDisponibility")
	public String deleteDisponibilities(@RequestParam(value = "id") long id) {

		disponibilityRepository.deleteById(id);
		return "ok";

	}

}
