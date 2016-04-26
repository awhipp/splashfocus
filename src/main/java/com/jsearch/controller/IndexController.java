package com.jsearch.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping({ "/", "/#", "index" })
	public ModelAndView getIndexPage(@RequestParam(required = false) String thanks,
			@RequestParam(required = false) String invalidUserAddress,
			@RequestParam(required = false) String invalidJobAddress, String logout) {
		ModelAndView m = new ModelAndView("index");
		String message = "";

		if (logout != null) {
			message = "Logged Out Successfully!";
		}

		if (thanks != null) {
			message = "Thanks for the Feedback!";
		}

		if (invalidUserAddress != null) {
			message = "Location you entered provided no user listings. Try again.";
		}

		if (invalidJobAddress != null) {
			message = "Location you entered provided no job listings. Try again.";
		}
		m.addObject("message", message);
		return m;
	}

	/**
	 *
	 * @param request
	 *            The GET request
	 * @return The highest role the user has
	 */
	private int getHighestRole(HttpServletRequest request) {
		int role = -1;

		if (request.isUserInRole("ROLE_UNVERIFIED")) {
			role = 0;
		}

		if (request.isUserInRole("ROLE_USER")) {
			role = 1;
		}

		if (request.isUserInRole("ROLE_ADMIN")) {
			role = 2;
		}

		return role;
	}

	/**
	 * Access Denied Handler
	 *
	 * @param request
	 *            The GET request
	 * @param user
	 *            The user or lack of user making the request
	 * @return The proper page
	 */
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accessDenied(HttpServletRequest request, Principal user) {
		switch (getHighestRole(request)) {
		case 0: // UNVERIFIED
			return new ModelAndView("redirect:/unverified");
		case 1: // USER
			return new ModelAndView("redirect:/dashboard");
		case 2: // ADMIN
			return new ModelAndView("missingPage", "message", "This is a bug. You are an admin. Report this please.");
		default: // Not Logged In
			ModelAndView m = new ModelAndView("redirect:/login");
			m.addObject("authfailed", "Need to be logged in.");
			return m;
		}

	}

	@RequestMapping(value = "/missingPage", method = RequestMethod.GET)
	public ModelAndView redirectToErrorPage(HttpServletRequest request, Principal user) {
		return new ModelAndView("redirect:/404");
	}

	@RequestMapping(value = "/backendError", method = RequestMethod.GET)
	public ModelAndView redirectToBackendError(HttpServletRequest request, Principal user) {
		return new ModelAndView("redirect:/500");
	}

	@RequestMapping(value = "/404", method = RequestMethod.GET)
	public ModelAndView getErrorPage(HttpServletRequest request, Principal user) {
		return new ModelAndView("404");
	}

	@RequestMapping(value = "/500", method = RequestMethod.GET)
	public ModelAndView getBackendError(HttpServletRequest request, Principal user) {
		return new ModelAndView("500");
	}

	@RequestMapping(value = "/unverified")
	public ModelAndView getUnverifiedPage() {
		return new ModelAndView("unverified");
	}

	/**
	 * This page will serve as a generic page to handle lost
	 * passwords/verification/etc
	 *
	 * @return The update page
	 */
	@RequestMapping(value = "/update/{url}")
	public ModelAndView getUpdatePage() {
		return new ModelAndView("update");
	}

}
