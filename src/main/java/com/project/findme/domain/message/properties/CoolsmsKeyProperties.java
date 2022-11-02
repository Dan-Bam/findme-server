package com.project.findme.domain.message.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter @Setter
@Component
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties("coolsms")
public class CoolsmsKeyProperties {

    private String access;
    private String secret;

}
