package com.jsearch.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import com.jsearch.util.ValidationUtil.Validate;

public class ValidationUtilTest {

	/**
	 * Naive test but only validated accounts will matter anyway
	 *
	 * @throws SQLException
	 */
	@Test
	public void testValidateUsernameEmail() {

		assertTrue(ValidationUtil.validate("test4@gmail.com", Validate.username_valid));
		assertTrue(ValidationUtil.validate("te+st4@gmail.com", Validate.username_valid));
		assertTrue(ValidationUtil.validate("te.st4@gmail.com", Validate.username_valid));
		assertTrue(ValidationUtil.validate("userTest@gmail.com", Validate.username_valid));

		assertFalse(ValidationUtil.validate("test4", Validate.username_valid));
		assertFalse(ValidationUtil.validate("@com", Validate.username_valid));
		assertFalse(ValidationUtil.validate("com@", Validate.username_valid));
	}

	@Test
	public void testValidateFirstName() throws SQLException {
		assertTrue(ValidationUtil.validate("Word", Validate.name_valid));
		assertTrue(ValidationUtil.validate("AB", Validate.name_valid));
		assertTrue(ValidationUtil.validate("ABC", Validate.name_valid));
		assertFalse(ValidationUtil.validate("1", Validate.name_valid));
		assertFalse(ValidationUtil.validate("11", Validate.name_valid));
		assertFalse(ValidationUtil.validate("AB1", Validate.name_valid));
		assertFalse(ValidationUtil.validate(" ", Validate.name_valid));
		assertFalse(ValidationUtil.validate("  ", Validate.name_valid));
		assertFalse(ValidationUtil.validate("   A", Validate.name_valid));
		assertFalse(ValidationUtil.validate("A  ", Validate.name_valid));
		assertFalse(ValidationUtil.validate(" A  ", Validate.name_valid));
		assertFalse(ValidationUtil.validate("A", Validate.name_valid));
	}

	@Test
	public void testValidateLastName() throws SQLException {
		assertTrue(ValidationUtil.validate("Word", Validate.name_valid));
		assertTrue(ValidationUtil.validate("AB", Validate.name_valid));
		assertTrue(ValidationUtil.validate("ABC", Validate.name_valid));
		assertFalse(ValidationUtil.validate("1", Validate.name_valid));
		assertFalse(ValidationUtil.validate("11", Validate.name_valid));
		assertFalse(ValidationUtil.validate("AB1", Validate.name_valid));
		assertFalse(ValidationUtil.validate(" ", Validate.name_valid));
		assertFalse(ValidationUtil.validate("  ", Validate.name_valid));
		assertFalse(ValidationUtil.validate("   A", Validate.name_valid));
		assertFalse(ValidationUtil.validate("A  ", Validate.name_valid));
		assertFalse(ValidationUtil.validate(" A  ", Validate.name_valid));
		assertFalse(ValidationUtil.validate("A", Validate.name_valid));
	}

	@Test
	public void testValidatePassword() throws SQLException {
		for (int i = 0; i < 35; i++) {
			if (i <= 6 || i >= 33) {
				assertFalse(ValidationUtil.validate(RandomStringUtils.random(i), Validate.password_valid));
			} else {
				assertTrue(ValidationUtil.validate(RandomStringUtils.random(i), Validate.password_valid));
			}
		}
	}

	@Test
	public void testValidateInterest() {
		for (int i = -1; i < 20; i++) {
			boolean result = ValidationUtil.validateInterest(i);
			if (i >= 0 && i <= 16) {
				assertTrue(result);
			} else {
				assertFalse(result);
			}
		}
	}

	@Test
	public void testValidateCity() {
		assertTrue(ValidationUtil.validate("Word", Validate.city_or_state_valid));
		assertTrue(ValidationUtil.validate("AB", Validate.city_or_state_valid));
		assertTrue(ValidationUtil.validate("ABC", Validate.city_or_state_valid));
		assertFalse(ValidationUtil.validate("1", Validate.city_or_state_valid));
		assertFalse(ValidationUtil.validate("11", Validate.city_or_state_valid));
		assertFalse(ValidationUtil.validate("AB1", Validate.city_or_state_valid));
		assertFalse(ValidationUtil.validate(" ", Validate.city_or_state_valid));
		assertFalse(ValidationUtil.validate("  ", Validate.city_or_state_valid));
		assertFalse(ValidationUtil.validate("   A", Validate.city_or_state_valid));
		assertFalse(ValidationUtil.validate("A  ", Validate.city_or_state_valid));
		assertFalse(ValidationUtil.validate(" A  ", Validate.city_or_state_valid));
		assertFalse(ValidationUtil.validate("A", Validate.city_or_state_valid));
	}

	@Test
	public void testValidateState() {
		assertTrue(ValidationUtil.validate("Word", Validate.city_or_state_valid));
		assertTrue(ValidationUtil.validate("AB", Validate.city_or_state_valid));
		assertTrue(ValidationUtil.validate("ABC", Validate.city_or_state_valid));
		assertFalse(ValidationUtil.validate("1", Validate.city_or_state_valid));
		assertFalse(ValidationUtil.validate("11", Validate.city_or_state_valid));
		assertFalse(ValidationUtil.validate("AB1", Validate.city_or_state_valid));
		assertFalse(ValidationUtil.validate(" ", Validate.city_or_state_valid));
		assertFalse(ValidationUtil.validate("  ", Validate.city_or_state_valid));
		assertFalse(ValidationUtil.validate("   A", Validate.city_or_state_valid));
		assertFalse(ValidationUtil.validate("A  ", Validate.city_or_state_valid));
		assertFalse(ValidationUtil.validate(" A  ", Validate.city_or_state_valid));
		assertFalse(ValidationUtil.validate("A", Validate.city_or_state_valid));
	}

	@Test
	public void testValidateZip() {
		assertFalse(ValidationUtil.validate("1", Validate.zip_valid));
		assertFalse(ValidationUtil.validate("10", Validate.zip_valid));
		assertFalse(ValidationUtil.validate("100", Validate.zip_valid));
		assertFalse(ValidationUtil.validate("1000", Validate.zip_valid));
		assertTrue(ValidationUtil.validate("10000", Validate.zip_valid));
		assertFalse(ValidationUtil.validate("100000", Validate.zip_valid));
	}
}
