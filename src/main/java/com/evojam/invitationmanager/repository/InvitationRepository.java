package com.evojam.invitationmanager.repository;

import com.evojam.invitationmanager.domain.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Long> {
}
