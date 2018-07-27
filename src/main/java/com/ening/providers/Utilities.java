package com.ening.providers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ening.entities.Disponibility;
import com.ening.entities.Patient;
import com.ening.entities.User;

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

		return (u.getUserLastName().substring(0, 1) + "" + u.getUserFirstName().replace(" ", "")).toUpperCase();
	}

	static String buildPatientUserName(Patient u) {

		return (u.getUserLastName().substring(0, 1) + "" + u.getUserFirstName().replace(" ", "")).toUpperCase();
	}

	static String todayDate() {
		final Date today = new Date();
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
		return sdf.format(today);

	}

	static boolean slotExist(String dateBegin, String dateEnd, List<Disponibility> disponibilities)
			throws ParseException {
		final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm aaa");

		Date begin = sdf.parse(dateBegin);
		Date end = sdf.parse(dateEnd);
		if (disponibilities != null) {

			for (Disponibility dispo : disponibilities) {

				if (begin.after(sdf.parse(dispo.getBeginDate())) && begin.before(sdf.parse(dispo.getBeginDate()))) {

					return true;
				}

				if (end.after(sdf.parse(dispo.getBeginDate())) && end.before(sdf.parse(dispo.getBeginDate()))) {

					return true;
				}
			}

		}

		return false;

	}

}
