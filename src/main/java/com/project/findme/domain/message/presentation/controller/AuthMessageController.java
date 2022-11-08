package com.project.findme.domain.message.presentation.controller;

import com.project.findme.domain.message.presentation.dto.CheckAuthKeyRequest;
import com.project.findme.domain.message.presentation.dto.PhoneNumberRequest;
import com.project.findme.domain.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthMessageController {

    private final MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody @Valid PhoneNumberRequest phoneNumberRequest) {
        messageService.sendMessage(phoneNumberRequest);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/check")
    public ResponseEntity<Void> checkAuthKey(@RequestBody @Valid CheckAuthKeyRequest checkAuthKeyRequest) {
        messageService.checkAuthKey(checkAuthKeyRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
