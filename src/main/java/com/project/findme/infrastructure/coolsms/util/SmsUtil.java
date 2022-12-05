package com.project.findme.infrastructure.coolsms.util;

public interface SmsUtil {

    void sendMessage(String phoneNumber, Integer authKey);

}
