package com.kalanso.coaching.Service;

import com.kalanso.coaching.Model.Notification;
import com.kalanso.coaching.Model.Ticket;
import com.kalanso.coaching.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceNotification {

    private final JavaMailSender mailSender;


    public void sendEmailNotification(String mail, String message) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(mail);
            helper.setSubject("Nouvelle Notification de Ticket");
            helper.setText(message, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
