package com.test.nti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NtiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NtiApplication.class, args);
	}

}
