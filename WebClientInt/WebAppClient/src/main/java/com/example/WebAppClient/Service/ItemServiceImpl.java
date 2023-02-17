package com.example.WebAppClient.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.WebAppClient.Model.Item;
import com.example.WebAppClient.Service.Interface.ItemService;

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
    public List<Item> getItemListByBizId(Long id) {
        Flux<Item> retrievedItemList = webClient.get()
                .uri("/item/get-list/"+id)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(response -> {
                    if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToFlux(Item.class);
                    } else {
                        return response.createException().flatMapMany(Flux::error);
                    }
                });
                
        return retrievedItemList.collectList().block();
    }

    @Override
    public Item getItemById (Long id){
        Mono<Item> retrievedItem = webClient.get() 
                .uri("/item/retrieve-id/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(response -> {
                	 if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(Item.class);
                    } else {
                        return response.createException().flatMap(Mono::error);
                    }
                });
        return retrievedItem.block();
    }


    @Override 
    public Item createItem (Item item){
        Mono<Item> createdItem = webClient.post()
                                                  .uri("/item/save")                                        
                                                  .body(Mono.just(item), Item.class)
                                                  .retrieve().bodyToMono(Item.class)
                                                  .timeout(Duration.ofMillis(10_000));
        return createdItem.block();
    }

    @Override
    public Item getItemByName (String name){
        Mono<Item> retrievedItem = webClient.get() 
                .uri("/item/retrieve-name/" + name)
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(response -> {
                	 if (response.statusCode().is2xxSuccessful()) {
                        return response.bodyToMono(Item.class);
                    } else {
                        return response.createException().flatMap(Mono::error);
                    }
                });
        return retrievedItem.block();
    }



	@Override
	public Boolean deleteItem(Long id) {
		 Mono<Boolean> retrievedItem = webClient.delete() 
	                .uri("/item/delete/" + id)
	                .accept(MediaType.APPLICATION_JSON)
	                .exchangeToMono(response -> {
	                    if (response.statusCode().equals(HttpStatus.OK)) {
	                        return response.bodyToMono(Boolean.class);
	                    } else {
	                        return response.createException().flatMap(Mono::error);
	                    }
	                });
	        return retrievedItem.block();
		
	}

    @Override
    public Item updateItem (Item item) {
        Mono<Item> updatedBusiness = webClient.put()
                .uri("/item/update")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(item), Item.class)
                .retrieve()
                .bodyToMono(Item.class);
        return updatedBusiness.block();
    }

}