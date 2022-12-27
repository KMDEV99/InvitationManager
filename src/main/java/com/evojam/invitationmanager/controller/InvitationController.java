package com.evojam.invitationmanager.controller;

import com.evojam.invitationmanager.domain.Invitation;
import com.evojam.invitationmanager.dto.InvitationDTO;
import com.evojam.invitationmanager.service.InvitationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/invitation", produces = { "application/json; charset=utf-8" })
@CrossOrigin
@ApiOperation("Invitation API")
public class InvitationController {

    private final InvitationService invitationService;

    public InvitationController(InvitationService invitationService) {
        this.invitationService = invitationService;
    }

    @GetMapping()
    public List<Invitation> getInvitations() {
        return invitationService.getInvitations();
    }

    @GetMapping("/{invitationId}")
    public Invitation getInvitationById(@PathVariable Long invitationId) {
        return invitationService.getInvitationById(invitationId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createInvitation(@RequestBody @Valid InvitationDTO invitationDTO) {
        invitationService.createInvitation(invitationDTO);
    }

    @PutMapping("/accept/{invitationId}")
    public void acceptInvitation(@PathVariable Long invitationId) {
        invitationService.acceptInvitation(invitationId);
    }

    @PutMapping("/decline/{invitationId}")
    public void declineInvitation(@PathVariable Long invitationId) {
        invitationService.declineInvitation(invitationId);
    }

}
