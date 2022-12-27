package com.evojam.invitationmanager.dto;

import com.evojam.invitationmanager.domain.Invitation;
import com.evojam.invitationmanager.domain.InvitationStatusEnum;
import com.evojam.invitationmanager.domain.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class InvitationDTO {

    @ApiModelProperty(value = "Invitation description", position = 1)
    @NotBlank(message = "Please enter Description")
    private String description;

    @ApiModelProperty(hidden = true)
    private User invitee;

    @ApiModelProperty(hidden = true)
    @Email(regexp = "[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Please enter proper Invitee Email")
    private String inviteeEmail;

    @ApiModelProperty(hidden = true)
    private InvitationStatusEnum status;

    @ApiModelProperty(value = "Inviter Email", position = 2)
    @NotNull(message = "Inviter Email cannot be empty")
    @Email(regexp = "[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Please enter proper Email")
    private String inviterEmail;

    public Invitation toInvitation() {
        return Invitation.builder()
                    .description(description)
                    .invitee(invitee)
                    .status(status)
                    .inviterEmail(inviterEmail)
                    .build();
    }
}
