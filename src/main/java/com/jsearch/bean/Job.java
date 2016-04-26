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

public class Job {

	private Integer id;
	private String company;
	private String position;
	private String recruiter;
	private String description;
	private String address;
	private Double latitude;
	private Double longitude;
	private Integer industry;
	private String tagline;
	private String metadata;

	private static final Logger LOG = Logger.getLogger(Job.class.getName());

	public Job() {
	}

	public Job(String id) {
		this.getJob(id);
	}

	public Job(Integer id, String company, String position, String recruiter, String description, String address,
			Double latitude, Double longitude, Integer industry, String tagline) {
		this.id = id;
		this.company = company;
		this.position = position;
		this.recruiter = recruiter;
		this.description = description;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.industry = industry;
		this.tagline = tagline;
		this.metadata = null;
	}

	/**
	 *
	 * @return the {@link Job}'s id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 *
	 * @param id
	 *            setting the {@link Job}'s id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 *
	 * @return the {@link Job}'s company
	 */
	public String getCompany() {
		return this.company;
	}

	/**
	 *
	 * @param company
	 *            setting the {@link Job}'s company
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 *
	 * @return the {@link Job}'s position
	 */
	public String getPosition() {
		return this.position;
	}

	/**
	 *
	 * @param position
	 *            setting the {@link Job}'s position
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 *
	 * @return the {@link Job}'s recruiter
	 */
	public String getRecruiter() {
		return this.recruiter;
	}

	/**
	 *
	 * @param recruiter
	 *            setting the {@link Job}'s recruiter
	 */
	public void setRecruiter(String recruiter) {
		this.recruiter = recruiter;
	}

	/**
	 *
	 * @return the {@link Job}'s description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 *
	 * @param description
	 *            setting the {@link Job}'s description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 *
	 * @return the {@link Job}'s address
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 *
	 * @param address
	 *            setting the {@link Job}'s address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 *
	 * @param latitude
	 *            setting the {@link Job}'s latitude
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 *
	 * @return the {@link Job}'s latitude
	 */
	public double getLatitude() {
		return this.latitude;
	}

	/**
	 *
	 * @param latitude
	 *            setting the {@link Job}'s longitude
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/**
	 *
	 * @return the {@link Job}'s longitude
	 */
	public double getLongitude() {
		return this.longitude;
	}

	/**
	 *
	 * @return industry setting the {@link User}'s industry
	 */
	public Integer getIndustry() {
		return this.industry;
	}

	/**
	 *
	 * @param industry
	 *            setting the {@link User}'s metindustryadata
	 */
	public void setIndustry(Integer industry) {
		this.industry = industry;
	}

	/**
	 *
	 * @return tagline setting the {@link Job}'s tagline
	 */
	public String getTagline() {
		return this.tagline;
	}

	/**
	 *
	 * @param tagline
	 *            setting the {@link Job}'s tagline
	 */
	public void setTagline(String tagline) {
		this.tagline = tagline;
	}

	/**
	 *
	 * @param metadata
	 *            setting the {@link Job}'s metadata
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 *
	 * @return the {@link Job}'s metadata
	 */
	public String getMetadata() {
		return this.metadata;
	}

	/**
	 *
	 * @param id
	 *            that you want the info for
	 * @return the {@link Job} information
	 */
	public Job getJob(String id) {

		ResultSet rs = null;
		try {
			String query = "SELECT * FROM `jobs` WHERE job_id = ?";
			Connection c = MySQLConnector.instance();
			PreparedStatement stmt = c.prepareStatement(query);
			stmt.setString(1, id);
			rs = stmt.executeQuery();

			while (rs.next()) {
				this.id = rs.getInt("job_id");
				this.company = rs.getString("company");
				this.position = rs.getString("position");
				this.recruiter = rs.getString("recruiter");
				this.description = rs.getString("description");
				this.address = rs.getString("address");
				this.latitude = rs.getDouble("latitude");
				this.longitude = rs.getDouble("longitude");
				this.industry = rs.getInt("industry");
				this.tagline = rs.getString("tagline");
				rs.close();
				return this;
			}
			rs.close();
			return null;
		} catch (Exception se) {
			LOG.info("Error: " + se);
			return null;
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

	private static List<Job> convertResultSetToList(ResultSet rs) {
		List<Job> jobs = new ArrayList<Job>();
		try {
			while (rs.next()) {
				Job j = new Job(rs.getInt("job_id"), rs.getString("company"), rs.getString("position"),
						ObfuscationUtil.cipher(rs.getString("recruiter")), rs.getString("description"),
						rs.getString("address"), rs.getDouble("latitude"), rs.getDouble("longitude"),
						rs.getInt("industry"), rs.getString("tagline"));
				j.setMetadata(ObfuscationUtil.cipher(j.getId().toString()));
				jobs.add(j);
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
		return jobs;
	}

	public void addJob(String recruiter) throws SQLException {
		double[] coords = GeoUtil.getCoordinates(this.address);
		this.latitude = coords[0];
		this.longitude = coords[1];

		String query = "INSERT INTO `jobs` "
				+ "(`company`, `position`, `recruiter`, `description`, `address`, `latitude`, `longitude`, `industry`, `tagline`) VALUES"
				+ "(?,?,?,?,?,?,?,?,?);";
		Connection c = MySQLConnector.instance();
		PreparedStatement stmt = c.prepareStatement(query);
		stmt.setString(1, this.company);
		stmt.setString(2, this.position);
		stmt.setString(3, recruiter);
		stmt.setString(4, this.description);
		stmt.setString(5, this.address);
		stmt.setDouble(6, this.latitude);
		stmt.setDouble(7, this.longitude);
		if (this.industry == null) {
			this.industry = 1;
		}
		stmt.setInt(8, this.industry);
		if (this.tagline == null) {
			this.tagline = "Seeking Willing Applicants!";
		}
		stmt.setString(9, this.tagline);
		stmt.executeUpdate();

		query = "UPDATE `users` SET `isRecruiter`=1 WHERE `username` = ?";
		stmt = c.prepareStatement(query);
		stmt.setString(1, recruiter);
		stmt.executeUpdate();
	}

	public static List<Job> getClosestList(double lat, double lng, int index, int fromIndex) throws SQLException {

		StringBuilder query = new StringBuilder("SELECT *,");
		query.append("(3959 * acos(cos(radians( ? )) * cos(radians(`latitude`)) * cos(radians(`longitude`) ");
		query.append("- radians( ? )) + sin(radians( ? )) * sin(radians(`latitude`)))) ");
		query.append("AS `distance` FROM `jobs` HAVING `distance` <= 25  ORDER BY `distance` LIMIT ?,?");

		Connection c = MySQLConnector.instance();
		PreparedStatement stmt = c.prepareStatement(query.toString());
		stmt.setDouble(1, lat);
		stmt.setDouble(2, lng);
		stmt.setDouble(3, lat);
		stmt.setInt(4, index);
		stmt.setInt(5, fromIndex);
		ResultSet rs = stmt.executeQuery();

		return convertResultSetToList(rs);

	}

}
