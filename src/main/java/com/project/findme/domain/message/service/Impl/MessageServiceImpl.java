package com.project.findme.domain.message.service.Impl;

import com.project.findme.domain.message.domain.MessageAuth;
import com.project.findme.domain.message.exception.AuthKeyNotMatchException;
import com.project.findme.domain.message.exception.PhoneNumberNotFound;
import com.project.findme.domain.message.presentation.dto.CheckAuthKeyRequest;
import com.project.findme.domain.message.presentation.dto.PhoneNumberRequest;
import com.project.findme.infrastructure.coolsms.Coolsms;
import com.project.findme.domain.message.domain.repository.MessageAuthRepository;
import com.project.findme.domain.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Log4j2
@Service
@EnableAsync
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageAuthRepository messageAuthRepository;
    private final Coolsms coolsms;

    @Async
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendMessage(PhoneNumberRequest phoneNumberRequest) {
        Integer authKey = createAuthKey();
        coolsms.sendMessage(phoneNumberRequest.getPhoneNumber(), authKey);
        messageSave(phoneNumberRequest.getPhoneNumber(), authKey);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void messageSave(String phoneNumber, Integer authKey) {
        MessageAuth messageAuth = messageAuthRepository.findById(phoneNumber)
                .orElse(MessageAuth.builder()
                        .phoneNumber(phoneNumber)
                        .authKey(authKey)
                        .build());

        messageAuthRepository.save(messageAuth);
    }

    @Override
    public Integer createAuthKey() {
        return new Random().nextInt(8888) + 1111;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkAuthKey(CheckAuthKeyRequest checkAuthKeyRequest) {
        MessageAuth messageAuth = messageAuthRepository.findById(checkAuthKeyRequest.getPhoneNumber())
                .orElseThrow(() -> new PhoneNumberNotFound("핸드폰 번호를 찾을 수 없습니다."));

        if(!messageAuth.getAuthKey().equals(checkAuthKeyRequest.getAuthKey())) {
            throw new AuthKeyNotMatchException("인증키가 일치 하지 않습니다.");
        }
    }

}
