package com.example.WebAppApi.Controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.WebAppApi.Model.Item;
import com.example.WebAppApi.Service.ItemService;
import com.example.WebAppApi.Service.BusinessService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class ItemController {

    @Autowired 
    private ItemService itemService;
    

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.response-codes.ok.desc}"),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}", content = {
                    @Content(examples = { @ExampleObject(value = "") }) }),
            @ApiResponse(responseCode = "404", description = "${api.response-codes.notFound.desc}", content = {
                    @Content(examples = { @ExampleObject(value = "") }) }) })
   
                    


    @GetMapping("/item/get-list/{id}")
    public ResponseEntity<List<Item>> getItemListByBizId(@PathVariable("id") Long id){
        try {
            List<Item> items =  itemService.getItemList(id);
            System.out.println("business id" + id);

            if (items.isEmpty()) {
                List<Item> emptyItemList = new ArrayList<Item>();
                return new ResponseEntity<>(emptyItemList, HttpStatus.OK);
            }

            return new ResponseEntity<>(items, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }    


    @PostMapping("/item/save")
    public ResponseEntity<Item> saveCollection(@RequestBody Item item){
        try{
            Item savedItem = itemService.createItem(item);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
            
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/item/retrieve-name/{name}")
    public ResponseEntity<Item> getItemByName(@PathVariable("name") String name){    
        return new ResponseEntity<Item>(itemService.getItemByName(name), HttpStatus.OK);
    }

    @GetMapping("/item/retrieve-id/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") Long id){    
        return new ResponseEntity<Item>(itemService.getItemById(id), HttpStatus.OK);
    }
    

    @DeleteMapping("/item/delete/{id}")
    public ResponseEntity<Boolean> deleteFoodWasteItemById(@PathVariable Long id) {
            try {
            	itemService.deleteItemById(id);
                return new ResponseEntity<>(true,HttpStatus.OK);
            }catch(Exception e) {
            	return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @PutMapping("/item/update")
    public ResponseEntity<Item> updateItem(@RequestBody Item item) {
        try {
            System.out.println("saved item");
            Item savedItem = itemService.updateItem(item);
            
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
