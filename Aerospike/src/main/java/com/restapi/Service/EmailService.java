package com.restapi.Service;
import com.restapi.Email.EmailDetails;
import jakarta.inject.Singleton;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Singleton
public class EmailService {


    public static void sendEmail(EmailDetails emailDetails) {

        Properties properties = System.getProperties();

        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("hrishikeshnalavade22@gmail.com","czzpkkgkjyzvekps");
            }
        });

        Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress("hrishikeshnalavade@gmail.com"));
            message.setSubject(emailDetails.getSubject());
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailDetails.getTo()));
            message.setText(emailDetails.getMessage());

            Transport.send(message);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

