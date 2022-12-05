package com.project.findme.infrastructure.coolsms;

import com.project.findme.infrastructure.coolsms.property.CoolsmsProperties;
import com.project.findme.infrastructure.coolsms.util.SmsUtil;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
public class Coolsms implements SmsUtil {

    private final CoolsmsProperties coolsmsProperties;

    @Override
    public void sendMessage(String phoneNumber, Integer authKey) {
        Message coolsms = new Message(coolsmsProperties.getAccess(), coolsmsProperties.getSecret());

        HashMap<String, String> params = new HashMap<>();
        params.put("to", phoneNumber);
        params.put("from", coolsmsProperties.getPhone());
        params.put("type", "SMS");
        params.put("text", "findme 인증번호는 [ " + authKey + " ] 입니다.");
        params.put("app_version", "test app 1.2");

        try {
            coolsms.send(params);
        } catch (CoolsmsException e) {
            throw new IllegalArgumentException("메세지 발송에 실패하였습니다.");
        }
    }

}
