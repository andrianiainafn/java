package org.example.demo1;

import jakarta.activation.DataSource;
import jakarta.ejb.Stateless;
import jakarta.mail.*;
import jakarta.mail.internet.*;

import jakarta.activation.DataHandler; // Import Jakarta EE DataHandler
import java.io.InputStream;
import java.util.Properties;

@Stateless
public class EmailService {

    public void sendEmailWithAttachment(String to, String subject, String body, InputStream attachmentInputStream, String attachmentName) {
        final String username = "votre_email@gmail.com";
        final String password = "votre_mot_de_passe";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            // Création de la partie texte du message
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);

            // Création de la partie pièce jointe
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.setFileName(attachmentName);

            // Création du DataSource pour la pièce jointe
            DataSource dataSource = new InputStreamDataSource(attachmentInputStream, attachmentName);

            // Assignation du DataSource au DataHandler de la pièce jointe
            attachmentPart.setDataHandler(new DataHandler(dataSource));

            // Combinaison des parties dans un message multipart
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentPart);

            // Ajout du multipart au message
            message.setContent(multipart);

            // Envoi du message
            Transport.send(message);

            System.out.println("Email envoyé avec succès avec pièce jointe");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}