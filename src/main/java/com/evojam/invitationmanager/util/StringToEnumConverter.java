package com.evojam.invitationmanager.util;

import com.evojam.invitationmanager.domain.InvitationStatusEnum;
import org.springframework.core.convert.converter.Converter;

public class StringToEnumConverter implements Converter<String, InvitationStatusEnum> {

    @Override
    public InvitationStatusEnum convert(String source) {
        try {
            return InvitationStatusEnum.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
