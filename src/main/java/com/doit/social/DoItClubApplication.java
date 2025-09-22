package com.doit.social;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:/opt/doit/config/do-it.properties")
public class DoItClubApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoItClubApplication.class, args);
	}
}
