package com.proxis.providers;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.proxis.entities.Patient;
import com.proxis.entities.User;

public interface Utilities {

	static final String NUM_STRING = "0123456789";
	static final String ALPHA_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static final String ALPHA_NUM_STRING = ALPHA_STRING + NUM_STRING;

	static String randomString(int length, String characters) {

		StringBuilder word = new StringBuilder();

		while (word.length() < length) {

			int index = (int) (Math.random() * characters.length());
			word.append(characters.charAt(index));
		}

		return word.toString();
	}

	static String randomAlphaNumeric(int length) {

		return Utilities.randomString(length, ALPHA_NUM_STRING);
	}

	static String randomNumericString(int length) {

		return Utilities.randomString(length, NUM_STRING);
	}

	static String buildCode(String label, int n) {

		return label + "_" + Utilities.randomNumericString(n);
	}
	
	static String buildUserName(User u) {

		return (u.getUserLastName().substring(0,1)+""+u.getUserFirstName().replace(" ", "")).toUpperCase();
	}
	static String buildPatientUserName(Patient u) {

		return (u.getUserLastName().substring(0,1)+""+u.getUserFirstName().replace(" ", "")).toUpperCase();
	}
	
	static String todayDate(){
	    final 	Date today = new Date();
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");	
		return sdf.format(today);
		
	}

	
}