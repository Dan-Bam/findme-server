package com.project.findme.global.security.jwt.properties;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter @Setter
@Component
@RequiredArgsConstructor
@ConfigurationProperties(value = "jwt.secret")
public class JwtKeyProperties {

    private final String key;

}
