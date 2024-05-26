package com.organicfoods.util;

import java.sql.Timestamp;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
	ResourceBundle bundle = ResourceBundle.getBundle("email");
	
	public static EmailUtil getInstance() {
		return new EmailUtil();
	}

	public Boolean sendTo(String to_email, String subject, String message) {
		try {
			//Properties:
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port","587"); //TLS 587 SSL 465
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			
			//Create authenticator
			Authenticator auth = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					// TODO Auto-generated method stub
					return new PasswordAuthentication(bundle.getString("from"), bundle.getString("password"));
				}
			};
			
			//Create Email Session
			Session session = Session.getInstance(props, auth);
			
			//create message email
			MimeMessage msg = new MimeMessage(session);
			
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			
			//From
			msg.setFrom(new InternetAddress(bundle.getString("from"), bundle.getString("fromName")));
			//To
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to_email,false));
			
			//Subject
			msg.setSubject(subject);
			
			//Time
			msg.setSentDate(new Timestamp(System.currentTimeMillis()));

			//Body
//			msg.setText(message, "UTF-8");
			msg.setContent("<!DOCTYPE html>\r\n"
					+ "<html>\r\n"
					+ "<body>\r\n"
					+ "\r\n"
					+ "<h3>" + message + "</h3>\r\n"
					+ "\r\n"
					+ "<img src='https://content.wepik.com/statics/26127453/preview-page0.jpg' alt='Image'>\r\n"
					+ "\r\n"
					+ "</body>\r\n"
					+ "</html>\r\n", "text/html; charset=UTF-8");
			//Send
			Transport.send(msg);
			System.out.println("Email sent successfully!");
			return true;
		} catch (Exception e) {
			System.out.println("Error sending email: " + e.getMessage());
			return false;
		}
	}
}
