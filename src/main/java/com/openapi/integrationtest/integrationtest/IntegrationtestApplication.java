package com.openapi.integrationtest.integrationtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class IntegrationtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegrationtestApplication.class, args);
		System.out.println("Successfully");
	}


}
