package com.evojam.invitationmanager.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    @ApiModelProperty(position = 1)
    private Long id;

    @NotBlank
    @ApiModelProperty(value = "First name", position = 2)
    private String firstName;

    @NotBlank
    @ApiModelProperty(value = "Last name", position = 3)
    private String lastName;

    @Email(regexp = "[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Please enter proper Email")
    @NotBlank(message = "Please enter proper Email")
    @ApiModelProperty(value = "Email", position = 4)
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "invitee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    @ApiModelProperty(value = "User's invitations", position = 5)
    private List<Invitation> invitations = new LinkedList<>();

}
