package com.jsearch.util;

public class ConversionUtil {

	private ConversionUtil() {
	}

	public static String convert(int i) {
		switch (i) {
		case 0:
			return "Any Industry";
		case 1:
			return "Art / Design";
		case 2:
			return "Construction / Labor";
		case 3:
			return "Customer Service";
		case 4:
			return "Education";
		case 5:
			return "Engineering";
		case 6:
			return "Finance";
		case 7:
			return "Food Service";
		case 8:
			return "Government / Politics";
		case 9:
			return "Health Services";
		case 10:
			return "Legal / Law Enforcement";
		case 11:
			return "News / Social Media";
		case 12:
			return "Retail";
		case 13:
			return "Science / Technology";
		case 14:
			return "Transportaion";
		case 15:
			return "Volunteer / Charity";
		case 16:
			return "Other Industries";
		default:
			return "Any Industry";
		}
	}
}
