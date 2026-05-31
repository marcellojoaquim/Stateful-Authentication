package com.microservice.stateful_any_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StatefulAnyApiApplication {

	public static void main(String[] args) {

		SpringApplication application = new SpringApplication(StatefulAnyApiApplication.class);
		application.setWebApplicationType(WebApplicationType.SERVLET);
		application.run(args);
	}

}
