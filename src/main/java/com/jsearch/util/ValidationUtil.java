package com.jsearch.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.jsearch.database.MySQLConnector;

public class ValidationUtil {
	private static final Logger LOG = Logger.getLogger(ValidationUtil.class.getName());
	private static final Pattern numberPattern = Pattern.compile(".*\\d.*");
	private static final Pattern zipPattern = Pattern.compile("(^\\d{5}$)");

	private ValidationUtil() {

	}

	public static boolean validate(String parameter, Validate type) {
		if (type.equals(Validate.username_unique)) {
			return validateUniqueUsername(parameter);
		}

		if (type.equals(Validate.username_valid)) {
			return validateUsername(parameter);
		}

		if (type.equals(Validate.name_valid)) {
			return validateName(parameter);
		}

		if (type.equals(Validate.password_valid)) {
			return validatePassword(parameter);
		}

		if (type.equals(Validate.city_or_state_valid)) {
			return validateCityOrState(parameter);
		}

		if (type.equals(Validate.zip_valid)) {
			return validateZIP(parameter);
		}

		return false;

	}

	public enum Validate {
		username_unique, username_valid, name_valid, password_valid, city_or_state_valid, zip_valid;
	}

	private static boolean validateUniqueUsername(String parameter) {
		ResultSet rs = null;
		try {
			String query = "SELECT COUNT(`username`) as `count` from `users` where `username` = (?);";
			Connection c = MySQLConnector.instance();
			PreparedStatement stmt = c.prepareStatement(query);
			stmt.setString(1, parameter);
			rs = stmt.executeQuery();
			while (rs.next()) {
				int count = rs.getInt("count");
				if (count > 0) {
					rs.close();
					return false;
				}
			}
			rs.close();
		} catch (SQLException e) {
			LOG.warning("Error Validating Uniqueness: " + e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e2) {
					return false;
				}
			}
		}
		return true;
	}

	private static boolean validateUsername(String parameter) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(parameter);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

	private static boolean validateName(String name) {
		Matcher numberMatcher = numberPattern.matcher(name);
		return !numberMatcher.matches() && name.replace(" ", "").length() >= 2;
	}

	private static boolean validatePassword(String password) {
		return password.length() >= 7 && password.length() <= 32;
	}

	private static boolean validateCityOrState(String location) {
		Matcher numberMatcher = numberPattern.matcher(location);
		boolean b = !numberMatcher.matches();
		return b && location.replace(" ", "").length() >= 2;
	}

	private static boolean validateZIP(String zip) {
		return zipPattern.matcher(zip).matches();
	}

	public static boolean validateInterest(int interest) {
		return interest >= 0 && interest <= 16;
	}
}
