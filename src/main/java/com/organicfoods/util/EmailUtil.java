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
			msg.setFrom(bundle.getString("from"));
			
			//To
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to_email,false));
			
			//Subject
			msg.setSubject(subject);
			
			//Time
			msg.setSentDate(new Timestamp(System.currentTimeMillis()));

			//Body
			msg.setText(message, "UTF-8");
			
			//Send
			Transport.send(msg);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
