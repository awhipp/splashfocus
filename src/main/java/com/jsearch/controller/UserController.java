package com.jsearch.controller;

import java.security.Principal;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jsearch.annotation.VisibleForTesting;
import com.jsearch.bean.Job;
import com.jsearch.bean.User;
import com.jsearch.util.EmailUtil;
import com.jsearch.util.EmailUtil.Type;
import com.jsearch.util.ObfuscationUtil;
import com.jsearch.util.ValidationUtil;
import com.jsearch.util.ValidationUtil.Validate;

@Controller
public class UserController {
	private static final Logger LOG = Logger.getLogger(UserController.class.getName());
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	/**
	 * The primary route for getting the profile page. Simply returns the user's
	 * page (based on principal.getName() ie: email)
	 *
	 * @param principal
	 *            the user accessing the page
	 * @return the {@link ModelAndView} for the Profile page
	 */
	@RequestMapping("dashboard")
	public ModelAndView getDashboardPage(Principal principal) {
		User u = new User(principal.getName());
		return new ModelAndView("dashboard", "user", u);
	}

	/**
	 * If you are logged in and your Username matches the other then return your
	 * profile page If that does not match then return a read-only profiel
	 *
	 * @param principal
	 *            The user. Null if not logged in.
	 * @param profile
	 *            The obfuscated profile account username that is being
	 *            requested
	 * @return The {@link ModelAndView} for the profile view.
	 * @throws Exception
	 *             The exception if something occurs in {@link ObfuscationUtil}
	 */
	@RequestMapping(value = "/viewProfile/{profile}")
	public ModelAndView getSpecificProfilePage(Principal principal, @PathVariable("profile") String profile)
			throws Exception {
		String username = ObfuscationUtil.decipher(profile);
		if (principal != null && principal.getName().equals(username)) {
			return new ModelAndView("redirect:/dashboard");
		} else {
			return new ModelAndView("viewProfile", "user", new User(username));
		}

	}

	/**
	 * Returns the {@link Job} as a Obfuscated ID
	 *
	 * @param id
	 *            The obfuscated Job ID
	 * @return The {@link ModelAndView} for the Job view.
	 * @throws Exception
	 *             The exception if something occurs in {@link ObfuscationUtil}
	 */
	@RequestMapping(value = "/viewJob/{id}")
	public ModelAndView getSpecificJobPage(@PathVariable("id") String id) throws Exception {
		return new ModelAndView("viewJob", "job", new Job(ObfuscationUtil.decipher(id)));
	}

	/**
	 * The related GET request for the form on the addJob page
	 *
	 * @return The {@link ModelAndView} for the Job being submitted to the
	 *         addJob POST request
	 */
	@RequestMapping(value = "postJob", method = RequestMethod.GET)
	public ModelAndView getAddJobPage() {
		return new ModelAndView("postJob", "command", new Job());
	}

	/**
	 * Adds the job, if valid, via a form post if a user is logged in
	 *
	 * @param job
	 *            The {@link Job} object that is submitted to be added
	 * @param model
	 *            The incoming {@link ModelMap} from the form
	 * @return A redirect to their profile page
	 * @throws SQLException
	 *             An exception that occurs if there is an issue in addJob();
	 */
	@RequestMapping(value = "postJob", method = RequestMethod.POST)
	public ModelAndView addJob(@ModelAttribute("SpringWeb") Job job, ModelMap model, Principal principal)
			throws SQLException {
		job.addJob(principal.getName());
		return new ModelAndView("redirect:/dashboard");
	}

	/**
	 * The related GET request for the form on the create page Returns signed in
	 * users to their profile
	 *
	 * @param principal
	 *            The user, if logged in, otherwise null
	 * @param results
	 *            If their was an issue with user creation before
	 * @return The {@link ModelAndView} of the page
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public ModelAndView getCreateUserPage(Principal principal, String results) {
		if (principal != null) { // If the User is logged in redirect to profile
			return new ModelAndView("redirect:/dashboard");
		} else { // If the user is not logged in show create User page
			ModelAndView m = new ModelAndView("create", "command", new User());
			if (results != null) {
				m.addObject("results", results);
			}
			return m;
		}
	}

	/**
	 * The POST request that creates Users after they are validated
	 *
	 * @param user
	 *            The {@link User} object being created/validated
	 * @param model
	 *            The {@link ModelMap} from the Form POST
	 * @return The proper {@link ModelAndView} based on the results
	 */
	@RequestMapping(value = "createConfirm", method = RequestMethod.POST)
	public ModelAndView confirmOptIn(@ModelAttribute("SpringWeb") User user, ModelMap model) {
		try {
			user = user.trim();
			String results = validate(user);
			if (results.length() == 0) {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				user.createUser(); // verify input here for a second time
				if (EmailUtil.sendEmail(user, Type.welcome)) {
					return new ModelAndView("redirect:/dashboard");
				} else {
					ModelAndView m = new ModelAndView("redirect:/create", "command", new User());
					if (results != null) {
						m.addObject("results",
								"Error sending to Email provided - Please try again <a href='/feedback'>or contact us.</a>");
					}
					return m;
				}
			} else {
				ModelAndView m = new ModelAndView("redirect:/create", "command", new User());
				if (results != null) {
					m.addObject("results", results);
				}
				return m;
			}
		} catch (Exception e) {
			LOG.warning("Error Creating Account: " + e);
			ModelAndView m = new ModelAndView("redirect:/create", "command", new User());
			m.addObject("results", "Error Creating Acount - Please try again <a href='/feedback'>or contact us.</a>");
			return m;
		}
	}

	/**
	 *
	 * @param email
	 *            The username being compared against
	 * @return True if the user exists, False if not.
	 */
	@RequestMapping(value = "emailExists/{email:.+}", method = RequestMethod.GET)
	public @ResponseBody String verifyUsernameExists(@PathVariable("email") String email) {
		String taken = validateUsernameUnique(email).length() == 0 ? "true" : "false";
		String valid = ValidationUtil.validate(email, Validate.username_valid) ? "true" : "false";
		return taken + "," + valid;
	}

	/**
	 * Validates incoming users and creates the response string
	 *
	 * @param user
	 *            The {@link User} object being validated
	 * @return The {@link String} which resembles any errors (length == 0 if no
	 *         errors)
	 * @throws SQLException
	 *             The exception that occurs if there is errors validating
	 */
	private String validate(User user) throws SQLException {
		try {
			StringBuilder results = new StringBuilder("");

			results.append(validateUsername(user.getUsername()));
			results.append(validateFirstName(user.getFirstname()));
			results.append(validateLastName(user.getLastname()));
			results.append(validatePassword(user.getPassword()));
			results.append(validateInterest(user.getInterest()));
			results.append(validateCity(user.getCity()));
			results.append(validateState(user.getState()));
			results.append(validateZip(user.getZipcode()));

			if (results.length() > 0) {
				results.setLength(results.length() - 1);
			}
			return results.toString();
		} catch (Exception s) {
			LOG.warning("Error Validating User: " + s.getMessage());
			throw new IllegalArgumentException("Error Creating Account - Please try again.");
		}
	}

	/**
	 * Validating Username of the {@link User} If username already exists, fail
	 * If username is not an email then fail If username is not unique then fail
	 *
	 * @param username
	 *            The username being validated
	 * @return A string of errors related to Username validation
	 * @throws SQLException
	 */
	private String validateUsername(String username) throws SQLException {
		return validateUsernameUnique(username) + validateUsernameEmail(username);
	}

	/**
	 * Checking against the database to ensure that the username is unique
	 *
	 * @param username
	 *            The username being validated
	 * @return An empty string if it succeeds
	 * @throws SQLException
	 *             An exception occurs if there was an error related to the
	 *             database
	 */
	private String validateUsernameUnique(String username) {
		return ValidationUtil.validate(username, Validate.username_unique) ? "" : "Email already exists,";
	}

	/**
	 * Checking to see if the username is an email
	 *
	 * @param username
	 *            The username being validated against an email regex
	 * @return The string representing any errors that occurred.
	 */
	protected String validateUsernameEmail(String username) {
		return ValidationUtil.validate(username, Validate.username_valid) ? "" : "Username is not an email,";
	}

	/**
	 * Validating the first name to be larger than length 2, and without numbers
	 *
	 * @param firstname
	 *            The {@link User}'s first name
	 * @return A string representing any errors
	 */
	@VisibleForTesting
	protected String validateFirstName(String firstname) {
		return ValidationUtil.validate(firstname, Validate.name_valid) ? ""
				: "First Name must have at least 2 valid characters,";
	}

	/**
	 * Validating the last name to be larger than length 2, and without numbers
	 *
	 * @param lastname
	 *            The {@link User}'s last name
	 * @return A string representing any errors
	 */
	@VisibleForTesting
	protected String validateLastName(String lastname) {
		return ValidationUtil.validate(lastname, Validate.name_valid) ? ""
				: "Last Name must have at least 2 valid characters,";
	}

	/**
	 * Validation for Passwords being within 7-32 characters in length
	 *
	 * @param password
	 *            The {@link User}'s password
	 * @return A string representing any errors
	 */
	@VisibleForTesting
	protected String validatePassword(String password) {
		return ValidationUtil.validate(password, Validate.password_valid) ? ""
				: "Password must be between 7-32 characters,";
	}

	/**
	 * Determines whether the interest given is valid
	 *
	 * @param interest
	 *            The entered interest
	 * @return Any resulting errors
	 */
	@VisibleForTesting
	protected String validateInterest(int interest) {
		return ValidationUtil.validateInterest(interest) ? "" : "Interest entered is invalid,";
	}

	/**
	 * Validating the city to be larger than length 2, and without numbers
	 *
	 * @param city
	 *            The {@link User}'s city
	 * @return A string representing any errors
	 */
	@VisibleForTesting
	protected String validateCity(String city) {
		return ValidationUtil.validate(city, Validate.city_or_state_valid) ? ""
				: "City must have at least 2 valid characters,";
	}

	/**
	 * Validating the state to be larger than length 2, and without numbers
	 *
	 * @param state
	 *            The {@link User}'s state
	 * @return A string representing any errors
	 */
	@VisibleForTesting
	protected String validateState(String state) {
		return ValidationUtil.validate(state, Validate.city_or_state_valid) ? ""
				: "State must have at least 2 valid characters,";
	}

	/**
	 * Checking to see if the username is an email
	 *
	 * @param username
	 *            The username being validated against an email regex
	 * @return The string representing any errors that occurred.
	 */
	@VisibleForTesting
	protected String validateZip(String zip) {
		return ValidationUtil.validate(zip, Validate.zip_valid) ? "" : "Zip needs to be in format #####,";
	}

}
