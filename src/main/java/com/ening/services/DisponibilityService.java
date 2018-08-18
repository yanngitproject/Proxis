package com.ening.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ening.entities.Disponibility;
import com.ening.repositories.DisponibiltyRepository;

@Service("disponibilityService")
public class DisponibilityService {



	public List<Disponibility> getDisponibilities(DisponibiltyRepository disponibilityRepository,String city, String speciality, String categorie, String slot)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		String todaystring = sdf.format(today);
		today = sdf.parse(todaystring);

		List<Disponibility> disponibilities = disponibilityRepository.findByStatus("free");
		List<Disponibility> result = new ArrayList<Disponibility>();
		for (Disponibility d : disponibilities) {

			if (today.after((sdf.parse(d.getStartDate()))) && today.before(sdf.parse(d.getEndDate()))) {

				if (d.getSlots() != null && d.getCategories() != null) {

					if (d.getSlots().contains(slot) && d.getCategories().contains(categorie)
							&& d.getCities().contains(city)) {

						Set<String> specialities = new HashSet<String>();

						if (d.getPractitioner().getExperiences() != null) {

							d.getPractitioner().getExperiences().forEach(item -> {
								specialities.add(item.getSpeciality());
							});
						}
						if (d.getPractitioner().getStudies() != null) {
							d.getPractitioner().getStudies().forEach(item -> {
								specialities.add(item.getSpeciality());
							});
						}

						if (specialities.contains(speciality)) {

							result.add(d);
						}

					}
				}

			}

		}
		return result;
	}

}
