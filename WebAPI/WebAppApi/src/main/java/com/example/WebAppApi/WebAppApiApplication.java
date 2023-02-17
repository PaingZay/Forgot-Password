package com.example.WebAppApi;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.WebAppApi.Model.Business;
import com.example.WebAppApi.Model.FoodWastePackage;
import com.example.WebAppApi.Model.Item;
import com.example.WebAppApi.Repository.ItemRepository;
import com.example.WebAppApi.Service.BusinessService;
import com.example.WebAppApi.Service.FoodWastePackageService;
import com.example.WebAppApi.Service.ItemService;

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
	CommandLineRunner commandLineRun(BusinessService businessSerivce, FoodWastePackageService foodwastepackageService, ItemService itemService, ItemRepository itemRepository) {
	  return args -> {
		businessSerivce.updatePassword("pyne469@gmail.com", "1234");

	 	//   String openingDays = "Mon , Tue, Wed, Thur, Fri, Sat, Sun";
	 	//   LocalTime openingTime =  LocalTime.of(10,00,00);
	 	//   LocalTime closingTime =  LocalTime .of(16,00,00);

	 	//   Business buz1  = new Business("breadtalk.amk@gmail.com","asdf","BreadTalk","Singapore","Bakery","Ang Mo Kio Hub", "123456" , "90844877", openingDays, openingTime, closingTime);
	 	   
		//   Business buz1Return = businessSerivce.createBusiness(buz1); 

	 	//   Business buz2  = new Business("koi.amk@gmail.com","asdf1234","Koi","Singapore","Bakery","Ang Mo Kio Hub", "123456" , "90844877", openingDays, openingTime, closingTime);
	 	//   businessSerivce.createBusiness(buz2);

			//   LocalTime start =  LocalTime.of(10,00,00);
			//   LocalTime end =  LocalTime.of(16,00,00);
			//   LocalDate pickup = LocalDate.now();
			//   FoodWastePackage c1 = new FoodWastePackage ("Package 1", 1, start, end, pickup, "Package of Breads", "Breads",buz1Return);
			//   foodwastepackageService.createPackage(c1);

			//   FoodWastePackage c2 = new FoodWastePackage ("Package 2", 1, start, end, pickup, "Cakes", "Mixed", buz1);
			//   c2.setStatus("Cancelled");
			//   foodwastepackageService.createPackage(c2);

			//   System.out.println (businessSerivce.getUserbyId(1L));


		// 	  Item item1 = new Item ("Cheese Cake", "Cake", 3, "This is cheese cake");
		// 	  itemService.createItem(item1);
		// 	  Item item2 = new Item ("Chocolate Cake", "Cake", 3, "This is chocolate cake");
		// 	  itemService.createItem(item2);


		// 	  List<Business> businesses = businessSerivce.getUserList();
		// 	  if (businesses!=null)
		// 	  {
		// 		System.out.println("User list is not empty");
		// 	  }

		// 	  List<FoodWastePackage> foodwastepackages = foodwastepackageService.getPackageList();
		// 	  if (foodwastepackages!=null)
		// 	  {
		// 		System.out.println("Collection list is not empty");
		// 	  }

		// 	  List<Item> items = itemService.getItemList();
		// 	  if (items!=null)
		// 	  {
		// 		System.out.println("Item list is not empty");
		// 	  }
			
		// 	  System.out.println(buz1);
		// 	  System.out.println(c1);
		// List<Item> items = itemService.getItemList(1L);
		// for(Item i: items){
		// 	System.out.println("Hello Print");
		// 	System.out.println(i);
		// }
	  };
	}
}
