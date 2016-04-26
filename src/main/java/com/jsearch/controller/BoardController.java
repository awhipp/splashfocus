package com.jsearch.controller;

import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.jsearch.bean.Job;
import com.jsearch.bean.User;
import com.jsearch.util.GeoUtil;

@Controller
public class BoardController {

	private final int INCREMENT = 20;

	/*****
	 *
	 * JOBS RELATED
	 *
	 *****/

	/**
	 * GET request for the nearbyJobs object for form mposts
	 *
	 * @return {@link ModelAndView} for nearbyJobs page
	 */
	@RequestMapping(value = "nearbyJobs", method = RequestMethod.GET)
	public ModelAndView getJobBoardPageRequest() {
		return new ModelAndView("nearbyJobs", "command", new Job());
	}

	/**
	 * A POST request function for when the user submits a new address request
	 *
	 * @param job
	 *            The job object with the items embedded
	 * @param model
	 * @param principal
	 *            The user or null making the request
	 * @return The new {@link ModelAndView} page
	 * @throws SQLException
	 */
	@RequestMapping(value = "nearbyJobs", method = RequestMethod.POST)
	public ModelAndView postJobBoardPageRequest(@ModelAttribute("SpringWeb") Job job, ModelMap model,
			Principal principal) throws SQLException {
		double[] coords = GeoUtil.getCoordinates(job.getAddress());
		if (coords[0] == -1 || coords[1] == -1) {
			ModelAndView m = new ModelAndView("nearbyJobs");
			m.getModelMap().put("address", job.getAddress());
			m.getModelMap().put("industry", job.getIndustry());
			m.getModelMap().put("min", 0);
			m.getModelMap().put("max", 0);
			return m;
		}
		
		List<Job> jobs = Job.getClosestList(coords[0], coords[1], ((int) job.getLatitude()),
				INCREMENT);

		ModelAndView m = new ModelAndView("nearbyJobs", "jobs", jobs);
		m.getModelMap().put("address", job.getAddress());
		m.getModelMap().put("industry", job.getIndustry());
		m.getModelMap().put("min", ((int) job.getLatitude() + INCREMENT));
		return m;
	}

	/*****
	 *
	 * USER RELATED
	 *
	 *****/

	/**
	 * GET request for the nearbyJobSeekers object for form posts
	 *
	 * @return {@link ModelAndView} for nearbyJobSeekers page
	 */
	@RequestMapping(value = "nearbyJobSeekers", method = RequestMethod.GET)
	public ModelAndView getUserBoardPageRequest() {
		return new ModelAndView("nearbyUsers", "command", new User());
	}

	/**
	 * A POST request function for when the user submits a new address request
	 *
	 * @param user
	 *            The user object with the items embedded
	 * @param model
	 * @param principal
	 *            The user or null making the request
	 * @return The new {@link ModelAndView} page
	 * @throws SQLException
	 */
	@RequestMapping(value = "nearbyJobSeekers", method = RequestMethod.POST)
	public ModelAndView postUserBoardPageRequest(@ModelAttribute("SpringWeb") User user, ModelMap model,
			Principal principal) throws SQLException {
		String address = user.getAddress();

		double[] coords = GeoUtil.getCoordinates(address);
		if (coords[0] == -1 || coords[1] == -1) {
			ModelAndView m = new ModelAndView("nearbyUsers");
			m.getModelMap().put("address", address);
			m.getModelMap().put("industry", user.getInterest());
			m.getModelMap().put("min", 0);
			return m;
		}

		String username = (principal == null ? "" : principal.getName());

		List<User> users = User.getClosestList(coords[0], coords[1], ((int) user.getLatitude()),
				INCREMENT, username);

		ModelAndView m = new ModelAndView("nearbyUsers", "users", users);
		m.getModelMap().put("address", address);
		m.getModelMap().put("industry", user.getInterest());
		m.getModelMap().put("min", ((int) user.getLatitude() + INCREMENT));
		return m;
	}
}
