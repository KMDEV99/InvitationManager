package com.evojam.invitationmanager.domain;

import com.evojam.invitationmanager.dto.InvitationDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(position = 1)
    private Long id;

    @NotBlank(message = "Description cannot be empty")
    @ApiModelProperty(value = "Invitation description", position = 2)
    private String description;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id")
    @JsonBackReference
    @NotNull(message = "Invitation has to have an invitee")
    private User invitee;

    @NotNull(message = "Inviter Email cannot be empty")
    @ApiModelProperty(value = "Inviter Email", position = 3)
    @Email(regexp = "[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Please enter proper Email")
    private String inviterEmail;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(value = "Invitation status", position = 4)
    private InvitationStatusEnum status = InvitationStatusEnum.PENDING;

    public InvitationDTO toInvitationDTO() {
        return InvitationDTO.builder()
                .description(description)
                .invitee(invitee)
                .status(status)
                .inviterEmail(inviterEmail)
                .build();
    }
}
