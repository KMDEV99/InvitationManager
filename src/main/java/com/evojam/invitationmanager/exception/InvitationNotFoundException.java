package com.evojam.invitationmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvitationNotFoundException extends RuntimeException {

    public InvitationNotFoundException(String message) {
        super(message);
    }
}
