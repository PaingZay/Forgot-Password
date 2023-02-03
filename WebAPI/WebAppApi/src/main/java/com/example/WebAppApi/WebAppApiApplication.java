package com.example.WebAppApi;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.WebAppApi.Model.Business;
import com.example.WebAppApi.Model.Collection;
import com.example.WebAppApi.Repository.BusinessRepository;


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
	CommandLineRunner commandLineRun(BusinessRepository BusinessRepo) {
	  return args -> {

		  String[] openingDays = {"Mon", "Tue", "Wed", "Thur", "Fri", "Sat", "Sun"};
		  LocalTime openingTime =  LocalTime.of(10,00,00);
		  LocalTime closingTime =  LocalTime.of(16,00,00);

		  Business buz1  = new Business("breadtalk.amk@gmail.com","asdf","Corporate User","BreadTalk","Singapore","Bakery","Ang Mo Kio Hub", "123456" , "90844877", openingDays, openingTime, closingTime);
		  BusinessRepo.save(buz1);

		  LocalTime start =  LocalTime.of(10,00,00);
		  LocalTime end =  LocalTime.of(16,00,00);
		  LocalDate pickup = LocalDate.now();
		  Collection c1 = new Collection ("Package 1", 1, start, end, pickup, "Breads", buz1);
		  List <Collection> collections = new ArrayList<>();
		  collections.add(c1);
	  };
	}

}
