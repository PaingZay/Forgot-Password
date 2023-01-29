package com.example.WebAppApi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.WebAppApi.Model.User;
import com.example.WebAppApi.Repository.userRepository;

@SpringBootApplication
public class WebAppApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebAppApiApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRun(userRepository UserRepo) {
	  return args -> {
		  System.out.println("");
	
		  User user1  = new User ("ishisan@gamil.com","asdf","Business");

		  UserRepo.save(user1);
		  
	  };
	}

}
