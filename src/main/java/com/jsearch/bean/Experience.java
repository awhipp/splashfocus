package com.jsearch.bean;

import java.sql.Date;

public class Experience {

	private Integer id;
	private String company;
	private String position;
	private String username;
	private String description;
	private Date start_date;
	private Date end_date;
	private String city;
	private String state;

	public Experience(Integer id, String company, String position, String username, String description, Date start_date,
			Date end_date, String city, String state) {
		this.id = id;
		this.company = company;
		this.position = position;
		this.username = username;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.city = city;
		this.state = state;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStart_date() {
		return this.start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return this.end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
