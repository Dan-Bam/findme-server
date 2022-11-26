package com.project.findme.domain.message.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash(value = "messageAuth", timeToLive = 60 * 10)
public class MessageAuth {

    @Id
    private String phoneNumber;

    @Length(max = 4)
    private Integer authKey;

}
