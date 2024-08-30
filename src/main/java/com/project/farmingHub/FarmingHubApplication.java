package com.project.farmingHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FarmingHubApplication {

	@GetMapping("/message")
	public String message(){
		return "Hello from docker...";
	}

	public static void main(String[] args) {
		SpringApplication.run(FarmingHubApplication.class, args);
		System.out.println("hello ! lets build an imagine project!");
	}

}
