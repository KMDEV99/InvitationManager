package com.evojam.invitationmanager.service;

import com.evojam.invitationmanager.domain.InvitationStatusEnum;
import com.evojam.invitationmanager.domain.User;
import com.evojam.invitationmanager.dto.InvitationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailSenderService {

    private JavaMailSender mailSender;

    public EmailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendInvitationEmail(InvitationDTO invitation) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(invitation.getInviterEmail());
        message.setTo(invitation.getInvitee().getEmail());
        message.setSubject("You have new pending invitation");
        message.setText("You have been invited to: " + invitation.getDescription());

        try {
            log.info("Sending Mail to: {}, from: {} \nbody: {}", invitation.getInvitee().getEmail(), invitation.getInviterEmail(), message.getText());
            mailSender.send(message);
        } catch (MailException e) {
            log.warn("Error sending email, make sure You provided proper login data in application.properties");
        }

    }

    public void sendInvitationResponseEmail(InvitationDTO invitation, InvitationStatusEnum decision) {
        SimpleMailMessage message = new SimpleMailMessage();
        User invitee = invitation.getInvitee();

        message.setFrom(invitee.getEmail());
        message.setTo(invitation.getInviterEmail());
        message.setSubject("You have new pending invitation");

        String messageBody = invitee.getFirstName() +
                " " +
                invitee.getLastName() +
                " has " +
                decision.toString().toLowerCase() +
                " Your invitation.";

        message.setText(messageBody);

        try {
            log.info("Sending Mail to: {}, from: {} \nbody: {}", invitation.getInviterEmail(), invitee.getEmail(), message.getText());
            mailSender.send(message);
        } catch (MailException e) {
            log.warn("Error sending email, make sure You provided proper login data in application.properties");
        }

    }

}
