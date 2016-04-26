package com.jsearch.controller;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

public class UserControllerTest {

	UserController uc;
	final String empty = "";

	@Before
	public void setUp() {
		uc = new UserController();
	}

	/**
	 * Naive test but only validated accounts will matter anyway
	 */
	@Test
	public void testValidateUsernameEmail() {
		String failed = "Username is not an email,";

		assertEquals(empty, uc.validateUsernameEmail("test4@gmail.com"));
		assertEquals(empty, uc.validateUsernameEmail("te+st4@gmail.com"));
		assertEquals(empty, uc.validateUsernameEmail("te.st4@gmail.com"));
		assertEquals(empty, uc.validateUsernameEmail("userTest@gmail.com"));
		assertEquals(failed, uc.validateUsernameEmail("test4"));
		assertEquals(failed, uc.validateUsernameEmail("@com"));
		assertEquals(failed, uc.validateUsernameEmail("com@"));
	}

	@Test
	public void testValidateFirstName() throws SQLException {
		String failed = "First Name must have at least 2 valid characters,";

		assertEquals(empty, uc.validateFirstName("Word"));
		assertEquals(failed, uc.validateFirstName(" "));
		assertEquals(failed, uc.validateFirstName("  "));
		assertEquals(failed, uc.validateFirstName("   A"));
		assertEquals(failed, uc.validateFirstName("A  "));
		assertEquals(failed, uc.validateFirstName(" A  "));
		assertEquals(failed, uc.validateFirstName("A"));
		assertEquals(empty, uc.validateFirstName("AB"));
		assertEquals(empty, uc.validateFirstName("ABC"));
		assertEquals(failed, uc.validateFirstName("1"));
		assertEquals(failed, uc.validateFirstName("11"));
		assertEquals(failed, uc.validateFirstName("AB1"));
	}

	@Test
	public void testValidateLastName() throws SQLException {
		String failed = "Last Name must have at least 2 valid characters,";

		assertEquals(empty, uc.validateLastName("Word"));
		assertEquals(failed, uc.validateLastName(" "));
		assertEquals(failed, uc.validateLastName("  "));
		assertEquals(failed, uc.validateLastName("   A"));
		assertEquals(failed, uc.validateLastName("A  "));
		assertEquals(failed, uc.validateLastName(" A  "));
		assertEquals(failed, uc.validateLastName("A"));
		assertEquals(empty, uc.validateLastName("AB"));
		assertEquals(empty, uc.validateLastName("ABC"));
		assertEquals(failed, uc.validateLastName("1"));
		assertEquals(failed, uc.validateLastName("11"));
		assertEquals(failed, uc.validateLastName("AB1"));
	}

	@Test
	public void testValidatePassword() throws SQLException {
		for (int i = 0; i < 35; i++) {
			String randomStringOfLengthI = uc.validatePassword(RandomStringUtils.random(i));
			if (i <= 6 || i >= 33) {
				assertEquals("Password must be between 7-32 characters,", randomStringOfLengthI);
			} else {
				assertEquals(empty, randomStringOfLengthI);
			}
		}
	}

	@Test
	public void testValidateInterest() {
		String failed = "Interest entered is invalid,";
		for (int i = -1; i < 20; i++) {
			String result = uc.validateInterest(i);
			if (i >= 0 && i <= 16) {
				assertEquals(empty, result);
			} else {
				assertEquals(failed, result);
			}
		}
	}

	@Test
	public void testValidateCity() {
		String failed = "City must have at least 2 valid characters,";

		assertEquals(empty, uc.validateCity("Word"));
		assertEquals(failed, uc.validateCity(" "));
		assertEquals(failed, uc.validateCity("  "));
		assertEquals(failed, uc.validateCity("   A"));
		assertEquals(failed, uc.validateCity("A  "));
		assertEquals(failed, uc.validateCity(" A  "));
		assertEquals(failed, uc.validateCity("A"));
		assertEquals(empty, uc.validateCity("AB"));
		assertEquals(empty, uc.validateCity("ABC"));
		assertEquals(failed, uc.validateCity("1"));
		assertEquals(failed, uc.validateCity("11"));
		assertEquals(failed, uc.validateCity("AB1"));
	}

	@Test
	public void testValidateState() {
		String failed = "State must have at least 2 valid characters,";

		assertEquals(empty, uc.validateState("Word"));
		assertEquals(failed, uc.validateState(" "));
		assertEquals(failed, uc.validateState("  "));
		assertEquals(failed, uc.validateState("   A"));
		assertEquals(failed, uc.validateState("A  "));
		assertEquals(failed, uc.validateState(" A  "));
		assertEquals(failed, uc.validateState("A"));
		assertEquals(empty, uc.validateState("AB"));
		assertEquals(empty, uc.validateState("ABC"));
		assertEquals(failed, uc.validateState("1"));
		assertEquals(failed, uc.validateState("11"));
		assertEquals(failed, uc.validateState("AB1"));
	}

	@Test
	public void testValidateZip() {
		String failed = "Zip needs to be in format #####,";

		assertEquals(failed, uc.validateZip("1"));
		assertEquals(failed, uc.validateZip("10"));
		assertEquals(failed, uc.validateZip("100"));
		assertEquals(failed, uc.validateZip("1000"));
		assertEquals(empty, uc.validateZip("10000"));
		assertEquals(failed, uc.validateZip("100000"));
	}

}