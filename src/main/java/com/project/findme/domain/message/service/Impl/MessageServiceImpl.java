package com.project.findme.domain.message.service.Impl;

import com.project.findme.domain.message.entity.MessageAuth;
import com.project.findme.domain.message.exception.AuthKeyNotMatchException;
import com.project.findme.domain.message.exception.PhoneNumberNotFound;
import com.project.findme.domain.message.presentation.dto.CheckAuthKeyRequest;
import com.project.findme.domain.message.presentation.dto.PhoneNumberRequest;
import com.project.findme.domain.message.properties.CoolsmsKeyProperties;
import com.project.findme.domain.message.repository.MessageAuthRepository;
import com.project.findme.domain.message.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Random;

@Log4j2
@Service
@EnableAsync
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageAuthRepository messageAuthRepository;
    private final CoolsmsKeyProperties coolsmsKeyProperties;

    @Async
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendMessage(PhoneNumberRequest phoneNumberRequest) {
        Integer authKey = createAuthKey();

        Message coolsms = new Message(coolsmsKeyProperties.getAccess(), coolsmsKeyProperties.getSecret());

        HashMap<String, String> params = new HashMap<>();
        params.put("to", phoneNumberRequest.getPhoneNumber());
        params.put("from", "01065466622");
        params.put("type", "SMS");
        params.put("text", "테스트 인증번호 " + authKey);
        params.put("app_version", "test app 1.2");

        try {
            coolsms.send(params);
        } catch (CoolsmsException e) {
            throw new IllegalArgumentException("메세지 발송에 실패하였습니다.");
        }

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
