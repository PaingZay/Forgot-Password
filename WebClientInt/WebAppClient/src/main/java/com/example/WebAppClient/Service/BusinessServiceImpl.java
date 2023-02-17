package com.example.WebAppClient.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import com.example.WebAppClient.DTO.FormData;
import com.example.WebAppClient.Model.Business;
import com.example.WebAppClient.Service.Interface.BusinessService;
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
                .exchangeStrategies(ExchangeStrategies.builder()
                .codecs(configurer -> configurer
                  .defaultCodecs()
                  .maxInMemorySize(16 * 1024 * 1024))
                  .build())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }


    //////// Create Business /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    
    @Override 
    public Business create (Business business){
        Mono<Business> createdBusiness = webClient.post()
                                                  .uri("/business/save")                                       
                                                  .body(Mono.just(business), Business.class)
                                                  .retrieve().bodyToMono(Business.class)
                                                  .timeout(Duration.ofMillis(10_000));
        return createdBusiness.block();
    }
    


    //////// Get Business List /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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


    //////// Get Business by ID /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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


    //////// Update Business /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public Business updateBusiness (Business business) {
        Mono<Business> updatedBusiness = webClient.put()
                .uri("/business/update")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(business), Business.class)
                .retrieve()
                .bodyToMono(Business.class);
        return updatedBusiness.block();
    }


    //////// Delete Business /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public Boolean deleteBusiness(Long id) {
        Mono<Boolean> deletedBusiness = webClient.delete()
                .uri("/business/delete/" + id)
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
        System.out.println (deletedBusiness);
        return deletedBusiness.block();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Business getBusinessbyEmail(String email) {
        Mono<Business> retrievedMember = webClient.get() 
                .uri("/business/getBusinessbyEmail/" + email)
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
    public Business updatePasswordByEmail(FormData formdata) {
        System.out.println("Form data" + formdata.getEmail() + "Form data" + formdata.getPassword());
        Mono<Business> updatedBusiness = webClient.put()
                .uri("/business/forgotpassword")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(formdata), FormData.class)
                .retrieve()
                .bodyToMono(Business.class);
        return updatedBusiness.block();
    }
}
