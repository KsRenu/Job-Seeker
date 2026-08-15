package org.example;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.List;
import java.util.Properties;

public class EmailSender {

    public static void sendEmail(String to, String subject, List<String> listItems) throws MessagingException {

        String from = "";
        String password = ""; // use App Password, not your real password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        // Build the body from the list
        StringBuilder body = new StringBuilder();
        body.append("Infosys Career Opportunities: ");
        int count = 1;
        for (String item : listItems) {
            body.append(count++).append(". ").append(item).append(",");
        }

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(body.toString());

        Transport.send(message);
        System.out.println("Email sent successfully!");
    }
}