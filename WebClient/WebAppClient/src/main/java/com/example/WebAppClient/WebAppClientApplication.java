package com.example.WebAppClient;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.example.WebAppClient.Model.Business;
import com.example.WebAppClient.Service.BusinessService;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class WebAppClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebAppClientApplication.class, args);
	}
		@Bean
		CommandLineRunner commandLineRun(BusinessService busienssService) {
		  return args -> {
	
			//   String[] openingDays = {"Mon", "Tue", "Wed", "Thur", "Fri", "Sat", "Sun"};
			//   LocalTime openingTime =  LocalTime.of(10,00,00);
			//   LocalTime closingTime =  LocalTime.of(16,00,00);
	
			//   Business buz1  = new Business("breadtalk.amk@gmail.com","asdf","Corporate User","BreadTalk","Singapore","Bakery","Ang Mo Kio Hub", "123456" , "90844877", openingDays, openingTime, closingTime);
			//   busienssService.create(buz1);

			//   Business buz2  = new Business("coffee.amk@gmail.com","asdf","Corporate User","BreadTalk","Singapore","Bakery","Ang Mo Kio Hub", "123456" , "90844877", openingDays, openingTime, closingTime);
			//   busienssService.create(buz2);
	
			//   LocalTime start =  LocalTime.of(10,00,00);
			//   LocalTime end =  LocalTime.of(16,00,00);
			//   LocalDate pickup = LocalDate.now();
			//   Collection c1 = new Collection ("Package 1", 1, start, end, pickup, "Breads", buz1);
			//   List <Collection> collections = new ArrayList<>();
			//   collections.add(c1);
		  };
	}
}

