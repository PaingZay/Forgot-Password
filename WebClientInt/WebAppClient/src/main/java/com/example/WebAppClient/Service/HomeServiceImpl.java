package com.example.WebAppClient.Service;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.WebAppClient.DTO.FormData;
import com.example.WebAppClient.Model.Business;
import com.example.WebAppClient.Model.OneTimePassword;
import com.example.WebAppClient.Service.Interface.HomeService;

import reactor.core.publisher.Mono;

@Service
public class HomeServiceImpl implements HomeService{
    
    @Autowired
    WebClient webClient;

    @Override 
    public Business authenticate (FormData formData){
    	try {
    		 Mono<Business> authenticatedBusiness = webClient.post()
                                                            .uri("/business/authenticate")                                         
                                                            .body(Mono.just(formData), FormData.class)
                                                            .retrieve().bodyToMono(Business.class)
                                                            .timeout(Duration.ofMillis(10_000));
            return authenticatedBusiness.block();	
    	}
        catch(Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    @Override 
    public OneTimePassword generateOTP (String email){
        System.out.print("HELLO CLASS");
        Mono<OneTimePassword> createdBusiness = webClient.post()
                                                  .uri("/generateOTP/" + email)                                       
                                                  .body(Mono.just(email), String.class)
                                                  .retrieve().bodyToMono(OneTimePassword.class)
                                                  .timeout(Duration.ofMillis(10_000));
        return createdBusiness.block();
    }

    @Override
    public OneTimePassword retrieveOTP (String email) {
        Mono<OneTimePassword> retrievedMember = webClient.get() 
                .uri("/OTP/retrieve/" + email)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(OneTimePassword.class);
                    } else {
                        return response.createException().flatMap(Mono::error);
                    }
                });
        return retrievedMember.block(); 
    }
}
