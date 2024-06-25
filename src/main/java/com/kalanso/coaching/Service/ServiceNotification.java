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

    private final NotificationRepository notificationRepository;
    private final JavaMailSender mailSender;

    public void envoyerNotification(Ticket ticket, String message) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setTicket(ticket);
        notification.setUser(ticket.getUser());

        // Save the notification in the database
        notificationRepository.save(notification);

        // Send the email notification
        sendEmailNotification(ticket.getUser().getEmail(), message);
    }

    public void modifierNotification(Long notificationId, String newMessage) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found for id :: " + notificationId));

        notification.setMessage(newMessage);
        notificationRepository.save(notification);
    }

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
