package com.example.WebAppClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class WebAppClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebAppClientApplication.class, args);

		// WebClient webClient = WebClient.create("http://localhost:8081/api");

		// Flux <User> retrievedUserList = webClient.get()
		// .uri("/users/")
		// .accept(MediaType.APPLICATION_JSON)
		// .exchangeToFlux(response -> {
		// if (response.statusCode().equals(HttpStatus.OK)) {
		// 	return response.bodyToFlux(User.class);	
		// } 
		// else {
		// 	return response.createException().flatMapMany(Flux::error);
		// }
		// });

		// Mono<User> retrievedUser = webClient.get()
		// .uri("/users/" + 1)
		// .accept(MediaType.APPLICATION_JSON)
		// .exchangeToMono(response -> {
		// if (response.statusCode().equals(HttpStatus.OK)) {
		// return response.bodyToMono(User.class);
		// } else {
		// return response.createException().flatMap(Mono::error);
		// }
		// });

	}
}
