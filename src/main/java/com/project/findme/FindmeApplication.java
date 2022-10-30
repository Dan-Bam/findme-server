package com.project.findme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class FindmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindmeApplication.class, args);
	}

}
