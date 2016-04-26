package com.jsearch.util;

import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.jsearch.bean.User;

public class EmailUtil {

	private static final Logger LOG = Logger.getLogger(EmailUtil.class.getName());
	private static Properties emailProperties;
	private static Session mailSession;
	private static Transport transport;
	private static final String emailPort = "587";
	private static final String emailHost = "smtp.gmail.com";
	private static final String username = "USERNAME";
	private static final String password = "PASSWORD";

	private EmailUtil() {
	}

	/**
	 * Initialization function. Sets up the server's mail properties.
	 */
	public static void init() {
		if (transport == null) {
			try {
				emailProperties = System.getProperties();
				emailProperties.put("mail.smtp.port", emailPort);
				emailProperties.put("mail.smtp.auth", "true");
				emailProperties.put("mail.smtp.starttls.enable", "true");
				mailSession = Session.getDefaultInstance(emailProperties, null);

				transport = mailSession.getTransport("smtp");
				transport.connect(emailHost, username, password);
			} catch (MessagingException e) {
				LOG.warning("Error initializing the Email Service." + e.getMessage() + " " + e.getCause());
			}
		}
	}

	/**
	 * Creates the subject, body, and recipients of the email
	 *
	 * @param user
	 *            The user that the email is being sent to
	 * @param type
	 *            The type of email to switch on
	 * @return An email object
	 * @throws MessagingException
	 *             If there is an issue with construction
	 */
	private static Message createEmail(User user, Type type) throws MessagingException {
		String subject = "Welcome to SplashFocus!";
		String body = "Hello " + user.getFirstname() + "!<br/><br/>"
				+ "Thank you for joining SplashFocus. To verify you account please follow this link: []";

		Message email = new MimeMessage(mailSession);

		email.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getUsername()));

		email.setSubject(subject);
		email.setContent(body, "text/html");

		return email;
	}

	/**
	 * Sends the actual email. Needs to be synchronized to send one at a time.
	 *
	 * @param user
	 *            The user being sent an email to
	 * @param type
	 *            The type of email being sent
	 * @return True if successful, False if not.
	 */
	public static synchronized boolean sendEmail(User user, Type type) {
		try {
			Message email = createEmail(user, type);
			transport.sendMessage(email, email.getAllRecipients());
		} catch (MessagingException e) {
			LOG.warning("Error sending E-mail: " + e.getMessage() + " " + e.getCause() + "To: " + user.getUsername()
					+ "\nType: " + type);
			return false;
		}
		LOG.info("Email sent\nTo: " + user.getUsername() + "\nType: " + type);
		return true;
	}

	/**
	 * Types of emails to switch on
	 *
	 * welcome - when user account created.
	 *
	 * new_verification - need a verification email resent.
	 *
	 * new_job - contains information and QR code.
	 *
	 * forgot_password - temporary password update.
	 *
	 * account_updated - when they change their protected information.
	 */
	public static enum Type {
		welcome, new_verification, new_job, forgot_password, account_updated;
	}
}
