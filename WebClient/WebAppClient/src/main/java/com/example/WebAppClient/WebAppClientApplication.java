package com.example.WebAppClient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.example.WebAppClient.DTO.RequestCollectionForm;
import com.example.WebAppClient.Model.Business;
import com.example.WebAppClient.Model.FoodWastePackage;
import com.example.WebAppClient.Model.Item;
import com.example.WebAppClient.Service.BusinessService;
import com.example.WebAppClient.Service.FoodWastePackageService;
import com.example.WebAppClient.Service.ItemService;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class WebAppClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebAppClientApplication.class, args);
	}
		@Bean
		CommandLineRunner commandLineRun(BusinessService businessService, FoodWastePackageService foodWastePackageService, ItemService itemService) {
		  return args -> {
	
			  String openingDays = "Mon , Tue, Wed, Thur, Fri, Sat, Sun";
			  LocalTime openingTime =  LocalTime.of(10,00,00);
			  LocalTime closingTime =  LocalTime.of(16,00,00);
	
			  Business buz1  = new Business("g@gmail.com","asdf12345","BreadTalk","Singapore","Bakery","Ang Mo Kio Hub", "123456" , "90844877", openingDays, openingTime, closingTime);
			  businessService.create(buz1);
	

			  LocalTime start =  LocalTime.of(10,00,00);
			  LocalTime end =  LocalTime.of(16,00,00);
			  LocalDate pickup = LocalDate.now();
			  RequestCollectionForm f1 = new RequestCollectionForm ("Package 10", 1, start, end, pickup, "This package has breads", "Breads",1L);
			  foodWastePackageService.createPackage(f1);


		  	  Item item1 = new Item ("Cheese Cake", "Cake", 3, "This is cheese cake");
		      itemService.createItem(item1);





			  List<Business> blist = businessService.getUserList();
			  if (blist!=null)
			  {
				System.out.println ("User list is not empty");
			  }
			  
			  List<FoodWastePackage> clist = foodWastePackageService.getPackageList();
			  if (clist != null)
			  {
				System.out.println("Collection List is not empty");
			  }

			  List<Item> ilist = itemService.getItemList();
			  if (ilist != null)
			  {
				System.out.println("Item List is not empty");
			  }


		  };
	}
}

