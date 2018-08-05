package com.ening.providers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Constants {

	public static final List<String> genders = Collections.unmodifiableList(Arrays.asList("MAN", "WOMAN"));
	public static final List<String> categories = Collections.unmodifiableList(Arrays.asList("CALL", "PHYSICAL"));
	public static final List<String> slots = Collections.unmodifiableList(Arrays.asList("18:00-22:00", "22:01-00:00","00:01-03:00","03:01-06:00"));


	public static final List<String> bloodsGroup = Collections
			.unmodifiableList(Arrays.asList("A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"));

	public static final List<String> specialities = Collections.unmodifiableList(Arrays.asList("Paediatrics",
			"Cardiology", "Neurology", "Neurology", "General practice", "Gynaecology", "Gastroenterology",
			"Ophthalmology", "Orthodontics", "Orthopaedics", "Radiology", "Surgery", "Urology", "Urology",
			"Anaesthesiology", "Immunology", "Psychiatry", "Midwife", "Dentist", "Dermatology", "Otorhinolaryngology",
			"Pharmacology", "Pulmonology", "Pulmonology", "Rehabilitation", "Biology"));

	public static final List<String> doualaCities = Collections.unmodifiableList(Arrays.asList("BONANJO", "AKWA",
			"KOUMASSI", "BALI", "BONAPRISO", "BONADOUMBE", "NDOGPASSI", "NDOGBATI", "KAMBO", "NKOLMBONG",
			"BONAMOUSSADI", "KOTO", "DENVER", "BESSEKE", "BONABERI", "BONTENE", "BONENDALE", "YONYONG", "MABANDA", "",
			"MAKEPE", "LOGPOM", "NDOGHEM", "LOGBESSOU", "MALANGUE", "BEEDI", "CITE DES PALMIERS", "OYAK", "LOGBABA",
			"NDOKOTI", "BASSA", "CITE SIC", "BEPANDA", "NEW DEIDO", "BONATEKI", "NGANGUE", "BONADOUMA", "YOUPWE",
			"BONANLOKAF", "NYLON", "NKOLOLOUN", "NDOGSIMBI", "MADAGASCAR", "CONGO", "PK10", "PK11", "PK8", "PK9",
			"PK14", "PK12", "PK13", "NYALLA", "VILLAGE"));
	

	
	public static final List<String> yaoundeCities = Collections.unmodifiableList(Arrays.asList("ANGUISSA", "BASTOS",
			"BIYEM-ASSI ", "BRIQUETERIE", "CAMP SIC HIPPODROME ", "ELIG EDZOA", "ETOUDI", "NLONGKAK", "ESSOS", "KOWEIT CITY",
			"MIMBOMAN", "MADAGASCAR", "MENDONG", "MOKOLO", "NGOUSSO", "NKONDENGUI", "OBILI", "OMNISPORTS", "SANTA BARBARA", "EMANA",
			"CAMP-SONEL", "ETOA-MEKI", "MVOG ATANGANA MBALLA", "MVOG-BETSI", "MVOG-MBI", "OLEMBE"));
	
	public static final List<String> grades = Collections.unmodifiableList(Arrays.asList("PhD", "ABD","Ms","OTHERS"));



	

}
