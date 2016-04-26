package com.jsearch.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jsearch.database.MySQLConnector;

public class Feedback {
	private String name;
	private String location;
	private String feedback;
	private String email;

	public Feedback() {

	}

	public Feedback(String name, String location, String feedback, String email) {
		this.name = name;
		this.location = location;
		this.feedback = feedback;
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void addFeedback() throws SQLException {

		String query = "INSERT INTO `feedback` (`name`, `location`, `feedback`, `email`) VALUES (?,?,?,?)";

		Connection c = MySQLConnector.instance();
		PreparedStatement stmt = c.prepareStatement(query);
		stmt.setString(1, this.name);
		stmt.setString(2, this.location);
		stmt.setString(3, this.feedback);
		stmt.setString(4, this.email);
		stmt.executeUpdate();
	}

	public static List<Feedback> getAllFeedback() throws SQLException {
		String query = "SELECT * FROM `feedback`";
		Connection c = MySQLConnector.instance();
		List<Feedback> participants = new ArrayList<Feedback>();
		PreparedStatement stmt = c.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			participants.add(new Feedback(rs.getString("name"), rs.getString("location"), rs.getString("feedback"),
					rs.getString("email")));
		}
		rs.close();
		return participants;
	}

}
