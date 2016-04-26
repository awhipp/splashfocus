package com.jsearch.bean;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UserTest {

	User user;

	@Test
	public void testTrim() {
		user = new User();
		user.setFirstname(" first name ");
		user.setLastname(" last name ");
		user.setUsername(" e@mail.com ");
		user.setCity(" city ");
		user.setState(" state ");
		user.setPassword("");
		user = user.trim();

		assertTrue(user.getFirstname().equals("first name"));
		assertTrue(user.getLastname().equals("last name"));
		assertTrue(user.getUsername().equals("e@mail.com"));
		assertTrue(user.getCity().equals("city"));
		assertTrue(user.getState().equals("state"));

	}
}