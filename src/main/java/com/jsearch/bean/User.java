package com.jsearch.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.jsearch.database.MySQLConnector;
import com.jsearch.util.GeoUtil;
import com.jsearch.util.ObfuscationUtil;

public class User {

	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private Double latitude;
	private Double longitude;
	private String city;
	private String state;
	private String zipcode;
	private Integer interest;
	private String tagline;
	private Integer isRecruiter;
	private Integer isExperienced;
	private String address;
	private List<Job> jobs;
	private List<Experience> experiences;

	private static final Logger LOG = Logger.getLogger(User.class.getName());

	public User() {
	}

	public User(String username) {
		this.getUser(username);
	}

	public User(String firstname, String lastname, String username, String password, Double latitude, Double longitude,
			String city, String state, String zipcode, Integer interest, String tagline, Integer isRecruiter,
			Integer isExperienced) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.longitude = longitude;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.isRecruiter = isRecruiter;
		this.isExperienced = isExperienced;

		if (isRecruiter != 0) {
			this.jobs = this.getJobs(username);
		} else {
			this.jobs = new ArrayList<Job>();
		}

		if (isExperienced != 0) {
			this.experiences = this.getExperiences(username);
		} else {
			this.experiences = new ArrayList<Experience>();
		}

		this.interest = interest;
		this.tagline = tagline;
		this.address = null;
	}

	/**
	 *
	 * @return the first name
	 */
	public String getFirstname() {
		return this.firstname;
	}

	/**
	 *
	 * @param firstname
	 *            the string to set the first name
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	/**
	 *
	 * @return the last name
	 */
	public String getLastname() {
		return this.lastname;
	}

	/**
	 *
	 * @param lastname
	 *            the string to set the last name
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 *
	 * @return the {@link User}'s name
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 *
	 * @param username
	 *            setting the {@link User}'s email username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 *
	 * @return the {@link User}'s name
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 *
	 * @param password
	 *            setting the {@link User}'s email password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 *
	 * @param latitude
	 *            setting the {@link User}'s latitude
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 *
	 * @return the {@link User}'s latitude
	 */
	public double getLatitude() {
		return this.latitude;
	}

	/**
	 *
	 * @param latitude
	 *            setting the {@link User}'s longitude
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 *
	 * @return the {@link User}'s longitude
	 */
	public double getLongitude() {
		return this.longitude;
	}

	/**
	 *
	 * @param city
	 *            setting the {@link User}'s city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 *
	 * @return the {@link User}'s city
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 *
	 * @param state
	 *            setting the {@link User}'s state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 *
	 * @return the {@link User}'s state
	 */
	public String getState() {
		return this.state;
	}

	/**
	 *
	 * @param zipcode
	 *            setting the {@link User}'s zipcode
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 *
	 * @return the {@link User}'s zipcode
	 */
	public String getZipcode() {
		return this.zipcode;
	}

	/**
	 *
	 * @return interest setting the {@link User}'s interest
	 */
	public Integer getInterest() {
		return this.interest;
	}

	/**
	 *
	 * @param interest
	 *            setting the {@link User}'s interest
	 */
	public void setInterest(Integer interest) {
		this.interest = interest;
	}

	/**
	 *
	 * @return tagline setting the {@link User}'s tagline
	 */
	public String getTagline() {
		return this.tagline;
	}

	/**
	 *
	 * @param tagline
	 *            setting the {@link User}'s tagline
	 */
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	/**
	 *
	 * @return isRecruiter setting the {@link User}'s isRecruiter
	 */
	public Integer getIsRecruiter() {
		return this.isRecruiter;
	}

	/**
	 *
	 * @param isRecruiter
	 *            setting the {@link User}'s isRecruiter
	 */
	public void setIsRecruiter(Integer isRecruiter) {
		this.isRecruiter = isRecruiter;
	}

	/**
	 *
	 * @return isExperienced setting the {@link User}'s isExperienced
	 */
	public Integer getIsExperienced() {
		return this.isExperienced;
	}

	/**
	 *
	 * @param isExperienced
	 *            setting the {@link User}'s isExperienced
	 */
	public void setIsExperienced(Integer isExperienced) {
		this.isExperienced = isExperienced;
	}

	/**
	 *
	 * @return address setting the {@link User}'s address
	 */
	public String getAddress() {
		return (this.address == null ? this.city + ", " + this.state + " " + this.zipcode : this.address);
	}

	/**
	 *
	 * @param address
	 *            setting the {@link User}'s address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 *
	 * @return the {@link User}'s jobs
	 */
	public List<Job> getJobs() {
		return this.jobs;
	}

	/**
	 *
	 * @param jobs
	 *            setting the {@link User}'s jobs
	 */
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	/**
	 *
	 * @return the {@link User}'s experiences
	 */
	public List<Experience> getExperiences() {
		return this.experiences;
	}

	/**
	 *
	 * @param experiences
	 *            setting the {@link User}'s experiences
	 */
	public void setExperiences(List<Experience> experiences) {
		this.experiences = experiences;
	}

	public synchronized void createUser() throws SQLException {
		double[] coords = GeoUtil.getCoordinates(this.city + " " + this.state + " " + this.zipcode);
		this.latitude = coords[0];
		this.longitude = coords[1];

		String query = "INSERT INTO `users` "
				+ "(`username`, `firstname`, `lastname`, `password`, `city`, `state`, `zipcode`, `interest`, `tagline`, `isRecruiter`, `isExperienced`, `latitude`, `longitude`) VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?);";
		Connection c = MySQLConnector.instance();
		PreparedStatement stmt = c.prepareStatement(query);
		stmt.setString(1, this.username);
		stmt.setString(2, this.firstname);
		stmt.setString(3, this.lastname);
		stmt.setString(4, this.password); // password
		stmt.setString(5, this.city);
		stmt.setString(6, this.state);
		stmt.setString(7, this.zipcode);
		stmt.setInt(8, this.interest);
		stmt.setString(9, "Actively seeking employment".trim());
		stmt.setInt(10, 0);
		stmt.setInt(11, 0);
		stmt.setDouble(12, this.latitude);
		stmt.setDouble(13, this.longitude);
		stmt.executeUpdate();

		query = "INSERT INTO `user_roles` (`username`, `ROLE`) VALUES (?,?)";
		stmt = c.prepareStatement(query);
		stmt.setString(1, this.username);
		stmt.setString(2, "ROLE_UNVERIFIED");
		stmt.executeUpdate();
	}

	/**
	 * Helper function to remove white space at beginning and end of specific
	 * variables, as well as trimming to max length allowed.
	 *
	 * 254 for username because emails cannot exceed 255, firstname / lastname
	 * is arbitrary city is 90 because longest city name is 80-something, state
	 * is 20 because longest is Rhode Island, 40 for password because it should
	 * be no longer than 32
	 */
	public User trim() {
		this.username = this.username.trim();

		if (this.username.length() > 254) {
			this.username = this.username.substring(0, 254);
		}

		this.firstname = this.firstname.trim();
		if (this.firstname.length() > 29) {
			this.firstname = this.firstname.substring(0, 29);
		}

		this.lastname = this.lastname.trim();
		if (this.lastname.length() > 29) {
			this.lastname = this.lastname.substring(0, 29);
		}

		this.password = this.password.trim();
		if (this.password.length() > 40) {
			this.password = this.password.substring(0, 40);
		}

		this.city = this.city.trim();
		if (this.city.length() > 90) {
			this.city = this.city.substring(0, 90);
		}

		this.state = this.state.trim();
		if (this.state.length() > 20) {
			this.state = this.state.substring(0, 20);
		}

		return this;
	}

	/**
	 *
	 * @param username
	 *            that you want the info for
	 * @return the {@link User} information
	 */
	public void getUser(String username) {
		ResultSet rs = null;
		try {
			String query = "SELECT * FROM `users` WHERE username = ?";
			Connection c = MySQLConnector.instance();
			PreparedStatement stmt = c.prepareStatement(query);
			stmt.setString(1, username);
			rs = stmt.executeQuery();

			while (rs.next()) {
				// Retrieved user by username should only return one

				this.firstname = rs.getString("firstname");
				this.lastname = rs.getString("lastname");
				this.username = username;
				this.password = ""; /**
									 * Password should never be sent to the
									 * frontend
									 **/
				this.latitude = rs.getDouble("latitude");
				this.longitude = rs.getDouble("longitude");
				this.city = rs.getString("city");
				this.state = rs.getString("state");
				this.zipcode = rs.getString("zipcode");
				this.isRecruiter = rs.getInt("isRecruiter");
				this.interest = rs.getInt("interest");
				this.tagline = rs.getString("tagline");
				if (this.isRecruiter != 0) {
					this.jobs = this.getJobs(username);
				} else {
					this.jobs = new ArrayList<Job>();
				}

				this.isExperienced = rs.getInt("isExperienced");
				if (this.isExperienced != 0) {
					this.experiences = this.getExperiences(username);
				} else {
					this.experiences = new ArrayList<Experience>();
				}

				// Always close ResultSet
			}
			rs.close();
		} catch (SQLException se) {
			LOG.info("Error: " + se);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				LOG.warning("Error closing ResultSet");
			}
		}
	}

	/**
	 *
	 * @param rs
	 *            The resultset for the query
	 * @return
	 */
	private static List<User> convertResultSetToList(ResultSet rs) {
		List<User> users = new ArrayList<User>();
		try {
			while (rs.next()) {
				User u = new User(rs.getString("firstname"), rs.getString("lastname"),
						ObfuscationUtil.cipher(rs.getString("username")), "", rs.getDouble("latitude"),
						rs.getDouble("longitude"), rs.getString("city"), rs.getString("state"), rs.getString("zipcode"),
						rs.getInt("interest"), rs.getString("tagline"), rs.getInt("isRecruiter"),
						rs.getInt("isExperienced"));
				u.setAddress(rs.getString("distance"));
				users.add(u);
			}
			rs.close();
		} catch (Exception e) {
			LOG.info("Error: " + e);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				LOG.warning("Error closing ResultSet");
			}
		}
		return users;
	}

	/**
	 *
	 * @param username
	 *            the recruiter's name
	 * @return a list of the recruiters jobs that they have posted
	 */
	private List<Job> getJobs(String username) {
		List<Job> jobs = new ArrayList<Job>();
		if (isRecruiter == 0) {
			return jobs;
		}

		ResultSet rs = null;
		try {
			String query = "SELECT * FROM `jobs` WHERE recruiter = ?";
			Connection c = MySQLConnector.instance();
			PreparedStatement stmt = c.prepareStatement(query);
			stmt.setString(1, username);
			rs = stmt.executeQuery();

			while (rs.next()) {
				// Retrieved user by username should only return one
				Job j = new Job(rs.getInt("job_id"), rs.getString("company"), rs.getString("position"), username, // recruiter
						// field
						rs.getString("description"), rs.getString("address"), rs.getDouble("latitude"),
						rs.getDouble("longitude"), rs.getInt("industry"), rs.getString("tagline"));
				j.setMetadata(ObfuscationUtil.cipher(j.getId().toString()));
				jobs.add(j);
			}

			// Always close ResultSet
			rs.close();
			return jobs;
		} catch (Exception se) {
			LOG.info("Error: " + se);
			return jobs;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				LOG.warning("Error closing ResultSet");
			}
		}
	}

	/**
	 *
	 * @param username
	 *            the recruiter's name
	 * @return a list of the user experiences that they have posted
	 */
	private List<Experience> getExperiences(String username) {
		List<Experience> experiences = new ArrayList<Experience>();
		if (isExperienced == 0) {
			return experiences;
		}

		ResultSet rs = null;
		try {
			String query = "SELECT * FROM `experiences` WHERE username = ?";
			Connection c = MySQLConnector.instance();
			PreparedStatement stmt = c.prepareStatement(query);
			stmt.setString(1, username);
			rs = stmt.executeQuery();

			while (rs.next()) {
				// Retrieved user by username should only return one
				Experience e = new Experience(rs.getInt("experience_id"), rs.getString("company"),
						rs.getString("position"), username, rs.getString("description"), rs.getDate("start_date"),
						rs.getDate("end_date"), rs.getString("city"), rs.getString("state"));
				experiences.add(e);
			}

			// Always close ResultSet
			rs.close();
			return experiences;
		} catch (Exception se) {
			LOG.info("Error: " + se);
			return experiences;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				LOG.warning("Error closing ResultSet");
			}
		}
	}

	public static List<User> getClosestList(double lat, double lng, int index, int fromIndex, String username)
			throws SQLException {

		/**
		 * http://sforsuresh.in/finding-nearest-location-using-latitude-
		 * longitude/
		 **/
		StringBuilder query = new StringBuilder("SELECT *,");
		query.append("(3959 * acos(cos(radians( ? )) * cos(radians(`latitude`)) * cos(radians(`longitude`) ");
		query.append("- radians( ? )) + sin(radians( ? )) * sin(radians(`latitude`)))) ");
		query.append("AS `distance` FROM `users` HAVING `enabled` = ? AND `username` <> ? AND `distance` <= 25 ");
		query.append("ORDER BY `distance` LIMIT ?,?");

		Connection c = MySQLConnector.instance();
		PreparedStatement stmt = c.prepareStatement(query.toString());
		stmt.setDouble(1, lat);
		stmt.setDouble(2, lng);
		stmt.setDouble(3, lat);
		stmt.setInt(4, 1);
		stmt.setString(5, username);
		stmt.setInt(6, index);
		stmt.setInt(7, fromIndex);
		ResultSet rs = stmt.executeQuery();

		return convertResultSetToList(rs);
	}
}
