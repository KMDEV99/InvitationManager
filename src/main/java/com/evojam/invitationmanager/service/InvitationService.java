package com.evojam.invitationmanager.service;

import com.evojam.invitationmanager.domain.Invitation;
import com.evojam.invitationmanager.domain.InvitationStatusEnum;
import com.evojam.invitationmanager.dto.InvitationDTO;
import com.evojam.invitationmanager.exception.InvitationNotFoundException;
import com.evojam.invitationmanager.exception.UserNotFoundException;
import com.evojam.invitationmanager.repository.InvitationRepository;
import com.evojam.invitationmanager.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class InvitationService {

    private final InvitationRepository invitationRepository;
    private final UserRepository userRepository;
    private final EmailSenderService emailSenderService;

    public InvitationService(InvitationRepository invitationRepository, UserRepository userRepository, EmailSenderService emailSenderService) {
        this.invitationRepository = invitationRepository;
        this.userRepository = userRepository;
        this.emailSenderService = emailSenderService;
    }

    public List<Invitation> getInvitations() {
        log.info("Getting all invitations");
        return invitationRepository.findAll();
    }

    public void createInvitation(InvitationDTO invitationDTO) {
        if (userRepository.findByEmail(invitationDTO.getInviteeEmail()) == null) {
            throw new UserNotFoundException("No invitee by Email: " + invitationDTO.getInviteeEmail());
        }
        invitationDTO.setInvitee(userRepository.findByEmail(invitationDTO.getInviteeEmail()));

        if (userRepository.findByEmail(invitationDTO.getInviterEmail()) == null) {
            throw new UserNotFoundException("No inviter by Email: " + invitationDTO.getInviterEmail());
        }
        invitationDTO.setStatus(InvitationStatusEnum.PENDING);

        invitationRepository.save(invitationDTO.toInvitation());
        emailSenderService.sendInvitationEmail(invitationDTO);
    }

    public void acceptInvitation(Long invitationId) {
        Invitation invitationToUpdate = invitationRepository.findById(invitationId)
                .orElseThrow(() -> new InvitationNotFoundException("No invitation by ID: " + invitationId));

        invitationToUpdate.setStatus(InvitationStatusEnum.ACCEPTED);

        emailSenderService.sendInvitationResponseEmail(invitationToUpdate.toInvitationDTO(), invitationToUpdate.getStatus());
        invitationRepository.save(invitationToUpdate);
    }

    public void declineInvitation(Long invitationId) {
        Invitation invitationToUpdate = invitationRepository.findById(invitationId)
                .orElseThrow(() -> new InvitationNotFoundException("No invitation by ID: " + invitationId));

        invitationToUpdate.setStatus(InvitationStatusEnum.DECLINED);

        emailSenderService.sendInvitationResponseEmail(invitationToUpdate.toInvitationDTO(), invitationToUpdate.getStatus());
        invitationRepository.save(invitationToUpdate);
    }

    public Invitation getInvitationById(Long invitationId) {
        return invitationRepository.findById(invitationId)
                .orElseThrow(() -> new InvitationNotFoundException("No invitation by ID: " + invitationId));
    }
}
