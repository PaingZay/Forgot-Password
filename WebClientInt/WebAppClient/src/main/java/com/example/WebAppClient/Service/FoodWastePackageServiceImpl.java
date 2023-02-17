package com.example.WebAppClient.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.WebAppClient.Model.FoodWastePackage;
import com.example.WebAppClient.Service.Interface.FoodWastePackageService;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodWastePackageServiceImpl implements FoodWastePackageService {


    @Autowired
    WebClient webClient;

    public FoodWastePackageServiceImpl(@Value("${content-service}") String baseURL) {
        this.webClient = WebClient.builder()
                .baseUrl(baseURL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }



    @Override
    public List<FoodWastePackage> getPackageList() {
        Flux<FoodWastePackage> retrievedPackageList = webClient.get()
                .uri("/foodwastepackage")
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToFlux(FoodWastePackage.class);
                    } else {
                        return response.createException().flatMapMany(Flux::error);
                    }
                });
        return retrievedPackageList.collectList().block();
    }


    @Override 
    public FoodWastePackage createPackage (FoodWastePackage foodWastePackage){
        Mono<FoodWastePackage> createdPackage = webClient.post()
                                                  .uri("/foodwastepackage/save")                                        
                                                  .body(Mono.just(foodWastePackage), FoodWastePackage.class)
                                                  .retrieve().bodyToMono(FoodWastePackage.class)
                                                  .timeout(Duration.ofMillis(10_000));
        System.out.println("In Service"+createdPackage);
        return createdPackage.block();
    }

    @Override
    public List<FoodWastePackage> getPendingList (Long id) {
        try
        {
        Flux<FoodWastePackage> retrievedPackageList = webClient.get()
                .uri("/foodwastepackage/get-list-pending/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToFlux(FoodWastePackage.class);
                    } else {
                        return response.createException().flatMapMany(Flux::error);
                    }
                });
        return retrievedPackageList.collectList().block();
        }
        catch(Exception e)
        {
            return new ArrayList<>();
        }
    }

    
    @Override
    public List<FoodWastePackage> getHistoryList (Long id) {
        try
        {
        Flux<FoodWastePackage> retrievedPackageList = webClient.get()
                .uri("/foodwastepackage/get-list-history/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToFlux(FoodWastePackage.class);
                    } else {
                        return response.createException().flatMapMany(Flux::error);
                    }
                });
        return retrievedPackageList.collectList().block();
        }
        catch(Exception e)
        {
            return new ArrayList<>();
        }
    }


    @Override
    public FoodWastePackage getPackagebyId (Long id) {

        Mono<FoodWastePackage> retrievedMember = webClient.get() 
                .uri("/foodwastepackage/retrieve/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToMono(FoodWastePackage.class);
                    } else {
                        return response.createException().flatMap(Mono::error);
                    }
                });
        return retrievedMember.block(); 
    }

    @Override
    public FoodWastePackage updateCollected(Long id) {
        Mono<FoodWastePackage> updatedMember = webClient.put()
                .uri("/foodwastepackage/update/collected/"+ id)
                .body(Mono.just(id), Long.class)
                .retrieve()  
                .bodyToMono(FoodWastePackage.class);
        return updatedMember.block();
    }

    @Override
    public FoodWastePackage updateCancelled(Long id) {
        Mono<FoodWastePackage> updatedMember = webClient.put()
                .uri("/foodwastepackage/update/cancelled/"+ id)
                .body(Mono.just(id), Long.class)
                .retrieve()
                .bodyToMono(FoodWastePackage.class);
        return updatedMember.block();
    }

    @Override
    public Boolean removeHistoryById(Long id) {
        Mono<Boolean> deletedHistory = webClient.delete()
                .uri("/foodwastepackage/history/remove/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    return Mono.error(NotFoundException::new);
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    return Mono.error(UnknownError::new);
                })
                .bodyToMono(Boolean.class)
                .onErrorComplete();
        return deletedHistory.block();
    }

    @Override
    public Boolean removeAllHistoryByBizId(Long id) {
        Mono<Boolean> deletedHistory = webClient.delete()
                .uri("/foodwastepackage/history/remove-all/"+ id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response -> {
                    return Mono.error(NotFoundException::new);
                })
                .onStatus(HttpStatus::is5xxServerError, response -> {
                    return Mono.error(UnknownError::new);
                })
                .bodyToMono(Boolean.class)
                .onErrorComplete();
        return deletedHistory.block();
    }
}