package com.evojam.invitationmanager.config;

import com.evojam.invitationmanager.domain.Invitation;
import com.evojam.invitationmanager.domain.InvitationStatusEnum;
import com.evojam.invitationmanager.domain.User;
import com.evojam.invitationmanager.repository.InvitationRepository;
import com.evojam.invitationmanager.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class LoadDatabase implements CommandLineRunner {

    private final InvitationRepository invitationRepository;
    private final UserRepository userRepository;


    public LoadDatabase(InvitationRepository invitationRepository, UserRepository userRepository) {
        this.invitationRepository = invitationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Preloading Data...");

        User konrad = User.builder()
                .firstName("Konrad")
                .lastName("Matuszewski")
                .email("matkonrad99@gmail.com")
                .build();

        User jan = User.builder()
                .firstName("Jan")
                .lastName("Kowalski")
                .email("jankowalh4ck3r@protonmail.com")
                .build();

        User joe = User.builder()
                .firstName("Joe")
                .lastName("Stachursky")
                .email("JoeStachurskyCEO@gmail.com")
                .build();

        Invitation invitationFromManager = Invitation.builder()
                .invitee(konrad)
                .description("Let's talk about future of our company")
                .status(InvitationStatusEnum.PENDING)
                .inviterEmail(joe.getEmail())
                .build();

        Invitation invitationFromEvojam = Invitation.builder()
                .invitee(konrad)
                .description("Invitation for technical interview")
                .status(InvitationStatusEnum.PENDING)
                .inviterEmail(jan.getEmail())
                .build();

        Invitation invitationToParty = Invitation.builder()
                .invitee(jan)
                .description("Hello, please come to my birthday party!")
                .status(InvitationStatusEnum.PENDING)
                .inviterEmail(konrad.getEmail())
                .build();

        userRepository.saveAll(List.of(konrad, jan, joe));
        invitationRepository.saveAll(List.of(invitationFromEvojam, invitationToParty, invitationFromManager));

        log.info("Preloading Data DONE");
    }
}
