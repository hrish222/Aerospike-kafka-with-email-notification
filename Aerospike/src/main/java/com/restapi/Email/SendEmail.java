package com.restapi.Email;

import jakarta.inject.Inject;

import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
    //private Session session;

    Message message;

    @Inject
    EmailProperties emailProperties;

    public SendEmail() {
        this.message = new MimeMessage(emailProperties.getSession());
    }

    public void sendEmail(EmailDetails emailDetails) {
        try {
            //Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("hrishikeshnalavade22@gmail.com"));
            message.setSubject(emailDetails.getSubject());
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailDetails.getTo()));
            message.setText(emailDetails.getMessage());

            Transport.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
