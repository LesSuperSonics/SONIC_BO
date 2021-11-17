package com.capgemini.candidateorganizationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CandidateOrganizationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CandidateOrganizationSystemApplication.class, args);
	}

}
