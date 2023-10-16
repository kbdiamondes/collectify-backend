package com.capstone.collectify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.capstone.collectify")
@EnableScheduling
public class DiscussionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscussionApplication.class, args);
	}

}
