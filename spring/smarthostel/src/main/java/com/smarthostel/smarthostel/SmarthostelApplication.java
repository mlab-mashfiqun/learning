package com.smarthostel.smarthostel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class SmarthostelApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmarthostelApplication.class, args);
	}

}
