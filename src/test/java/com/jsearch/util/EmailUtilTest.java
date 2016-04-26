package com.jsearch.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.jsearch.bean.User;
import com.jsearch.util.EmailUtil.Type;

public class EmailUtilTest {

	User user;

	@Before
	public void setUp() {
		EmailUtil.init();
		user = new User();
	}

	@Ignore
	@Test
	public void testSend() {
		user.setUsername("test4@gmail.com");
		assertTrue(EmailUtil.sendEmail(user, Type.welcome));

		user.setUsername("splashfocus");
		assertFalse(EmailUtil.sendEmail(user, Type.welcome));
	}
}
