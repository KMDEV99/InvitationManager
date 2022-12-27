package com.evojam.invitationmanager.dto;

import com.evojam.invitationmanager.domain.Invitation;
import com.evojam.invitationmanager.domain.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.LinkedList;
import java.util.List;

@Data
public class UserDTO {

    @NotBlank(message = "Please enter First Name")
    @ApiModelProperty(value = "First name", position = 1)
    private String firstName;

    @NotBlank(message = "Please enter Last Name")
    @ApiModelProperty(value = "Last name", position = 2)
    private String lastName;

    @Email(regexp = "[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Please enter proper Email")
    @NotBlank(message = "Please enter proper Email")
    @ApiModelProperty(value = "Email", position = 3)
    @Column(unique = true)
    private String email;

    @ApiModelProperty(value = "Invitations", hidden = true)
    private List<Invitation> invitations = new LinkedList<>();

    public User toUser() {
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .invitations(invitations)
                .build();
    }

}
