package com.ening.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ening.entities.Practitioner;
import com.ening.entities.Studie;
import com.ening.repositories.PractitionerRepository;

@Service("practitionerService")
public class PractitionerService {

	@Autowired
	PractitionerRepository practionerRepository;

	public List<Practitioner> getPractitionersBySpeciality(String speciality ,List<Practitioner> init) {

	
		if (init != null && !init.isEmpty()) {
			
			List<Practitioner> result = null ;

			for (Practitioner doctor : init) {
				
				for(Studie studie : doctor.getStudies() ){
					
					if(studie.getSpeciality().equals(speciality)){
									
					}

				}
			}
			
			return result;

		}

		return null;

	}

}
