package com.example.WebAppClient.Service;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.WebAppClient.DTO.FormData;
import com.example.WebAppClient.Model.Business;

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
}
