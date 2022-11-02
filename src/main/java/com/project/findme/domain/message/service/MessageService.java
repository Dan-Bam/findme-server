package com.project.findme.domain.message.service;

import com.project.findme.domain.message.presentation.dto.CheckAuthKeyRequest;
import com.project.findme.domain.message.presentation.dto.PhoneNumberRequest;

public interface MessageService {

    void sendMessage(PhoneNumberRequest phoneNumberRequest);
    void messageSave(String phoneNumber, Integer authKey);
    Integer createAuthKey();
    void checkAuthKey(CheckAuthKeyRequest checkAuthKeyRequest);

}
