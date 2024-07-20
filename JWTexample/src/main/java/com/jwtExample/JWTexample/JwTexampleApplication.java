package com.jwtExample.JWTexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jwtExample", "com.securityUtil"})
public class JwTexampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwTexampleApplication.class, args);
	}

}
