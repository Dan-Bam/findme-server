package com.project.findme.domain.image.properties;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(value = "cloud.aws.s3")
public class S3BucketProperties {

    private String bucket;

}
