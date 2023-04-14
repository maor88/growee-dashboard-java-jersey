/**
 * 
 */
package com.growee.emails;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.growee.objects.contactUs.ContactUsEmail;

/**
 * @author Maor
 *
 */
public class EmailSender {

	static String groweeEmail = "contact@getgrowee.com";
	static String groweeEmailName = "Growee";
	static String groweeEmailPass = "pass";
	static String host = "smtp.gmail.com";
	static String forgatPasswordPageUrl = "html/createNewPassword.html";
	
	//email to bugs department
	static String bugsEmail = "support@getgrowee.com";
	
	public static boolean sendForgotPassMail(String userEmail, String userName, String uniqCode, String baseUrl) {
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(groweeEmail, groweeEmailPass);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(groweeEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
			message.setSubject("Growee set password");
			message.setText("Dear " + userName
					+ ",\n\nForgot your password? No problem, click the link below to reset it.\n"
					+ baseUrl + forgatPasswordPageUrl + "?" + uniqCode + "\n\nBest regards,\nThe Growee team,");

			Transport.send(message);

			System.out.println("mail sent");

		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		}

		return false;
	}

	public static boolean sendContactUsMail(ContactUsEmail email) {
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(groweeEmail, groweeEmailPass);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(groweeEmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(bugsEmail));
			message.setSubject("mygrowee Contact us email");
			message.setText(email.toString());
			Transport.send(message);
			System.out.println("mail sent");

		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		}

		return false;
	}
	
	public static void sendUserWelcomMail(String userEmail, String userName) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(groweeEmail, groweeEmailPass);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(groweeEmail,groweeEmailName));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
			message.setSubject("Welcome to Growee!");
			String msg = "<div dir='ltr'> Hello "+userName+",<br>"
					+ "<div dir='ltr'>Thank you for signing up to Growee device!<br>"
					+ "<br><br>Still don't own a Growee?<br>Get it at our <a href ='https://www.getgrowee.com' target='_blank'>website</a> and upgrade your garden today. "
					+ "<br><br>Welcome aboard,<br>The Growee team.</div> <div>";
			message.setContent(msg, "text/html; charset=utf-8");
			Transport.send(message);
			System.out.println("mail sent");

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}
	
}
