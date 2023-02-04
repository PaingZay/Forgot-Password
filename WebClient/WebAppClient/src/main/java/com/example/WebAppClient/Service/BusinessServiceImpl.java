package com.example.WebAppClient.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.WebAppClient.Model.Business;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
public class BusinessServiceImpl implements BusinessService {


    @Autowired
    WebClient webClient;

    public BusinessServiceImpl(@Value("${content-service}") String baseURL) {
        this.webClient = WebClient.builder()
                .baseUrl(baseURL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }




    @Override
    public List<Business> getUserList() {
        Flux<Business> retrievedBusinessList = webClient.get()
                .uri("/business/get-list")
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToFlux(Business.class);
                    } else {
                        return response.createException().flatMapMany(Flux::error);
                    }
                });
        return retrievedBusinessList.collectList().block();
    }


    @Override
    public Business getUserbyId (Long id) {
        Mono<Business> retrievedMember = webClient.get() 
                .uri("/business/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(Business.class);
                    } else {
                        return response.createException().flatMap(Mono::error);
                    }
                });
        return retrievedMember.block(); 
    }


    @Override 
    public Business create (Business business){
        Mono<Business> createdBusiness = webClient.post()
                                                  .uri("/business/save")                                         //endpoint name of api
                                                  .body(Mono.just(business), Business.class)
                                                  .retrieve().bodyToMono(Business.class)
                                                  .timeout(Duration.ofMillis(10_000));
        return createdBusiness.block();
    }

}
