package com.project.findme.global.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@ConfigurationProperties(value = "jwt.secret")
public class JwtKeyProperties {

    private String key;

}
