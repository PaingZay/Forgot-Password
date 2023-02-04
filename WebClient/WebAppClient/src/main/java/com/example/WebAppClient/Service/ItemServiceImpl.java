package com.example.WebAppClient.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.WebAppClient.Model.Item;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {


    @Autowired
    WebClient webClient;

    public ItemServiceImpl(@Value("${content-service}") String baseURL) {
        this.webClient = WebClient.builder()
                .baseUrl(baseURL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }



    @Override
    public List<Item> getItemList() {
        Flux<Item> retrievedItemList = webClient.get()
                .uri("/item")
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        return response.bodyToFlux(Item.class);
                    } else {
                        return response.createException().flatMapMany(Flux::error);
                    }
                });
        return retrievedItemList.collectList().block();
    }


    @Override 
    public Item createItem (Item item){
        Mono<Item> createdItem = webClient.post()
                                                  .uri("/item")                                        
                                                  .body(Mono.just(item), Item.class)
                                                  .retrieve().bodyToMono(Item.class)
                                                  .timeout(Duration.ofMillis(10_000));
        return createdItem.block();
    }

}