package com.project.findme.global.security.jwt.property;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter @Setter
@Component
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(value = "jwt.secret")
public class JwtKeyProperties {

    private String key;

}
