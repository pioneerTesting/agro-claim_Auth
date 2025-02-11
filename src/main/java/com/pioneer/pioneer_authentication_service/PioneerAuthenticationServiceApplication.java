package com.pioneer.pioneer_authentication_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class PioneerAuthenticationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PioneerAuthenticationServiceApplication.class, args);
	}

}
