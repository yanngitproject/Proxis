package com.ening.providers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

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

	static List<Date> getDateBetween(Date begin, Date end) {

		List<Date> dates = new ArrayList<Date>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(begin);

		while (calendar.getTime().before(end)) {
			Date dateBetween = calendar.getTime();
			dates.add(dateBetween);
			calendar.add(Calendar.DATE, 1);
		}

		dates.add(end);

		return dates;

	}

	static Date setStringToDateWithoutHour(String s) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date = sdf.parse(s);
		return date;
	}

	static List<String> getParsesListFromJSON(List<String> myList) {

		List<String> newList = new ArrayList<>(myList.size());
		for (String s : myList) {

			newList.add(s.replace("\"", "").replace("[", "").replace("]", ""));
		}

		return newList;

	}

	static String getValideDate(String s) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy", Locale.ENGLISH);

		Date date = sdf.parse(s.substring(4, 15).replace(" ", "-"));
		
		return  new SimpleDateFormat("yyyy-MM-dd").format(date) ;
	}

}
