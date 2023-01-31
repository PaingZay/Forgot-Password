package com.example.WebAppApi;

import java.time.LocalTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.WebAppApi.Model.Business;
import com.example.WebAppApi.Model.User;
import com.example.WebAppApi.Repository.BusinessRepository;
import com.example.WebAppApi.Repository.UserRepository;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication

@OpenAPIDefinition(servers = {
	@Server(url = "http://localhost:8081", description = "embedded API webserver"),
	@Server(url = "http://192.168.1.116:8081", description = "localhost API webserver")
})

public class WebAppApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebAppApiApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRun(UserRepository UserRepo, BusinessRepository BusinessRepo) {
	  return args -> {
		  System.out.println("");
	
		  User user1  = new User ("ishisan@gamil.com","asdf","Business");
		  UserRepo.save(user1);

		  String[] openingDays = {"Mon", "Tue", "Wed", "Thur", "Fri", "Sat", "Sun"};
		  LocalTime openingTime =  LocalTime.of(10,00,00);
		  LocalTime closingTime =  LocalTime.of(16,00,00);
		  Business buz1  = new Business("Hello Class","Vietnam","Youtuber","Hochimin", "1990" , "099090901", openingDays, openingTime, closingTime);
		  BusinessRepo.save(buz1);
		  
	  };
	}

}
