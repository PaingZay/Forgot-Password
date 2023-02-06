package com.example.WebAppApi.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebAppApi.DTO.FormData;
import com.example.WebAppApi.Model.Business;
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
public class BusinessController {

    @Autowired 
    private BusinessService businessService;

    

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.response-codes.ok.desc}"),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}", content = {
                    @Content(examples = { @ExampleObject(value = "") }) }),
            @ApiResponse(responseCode = "404", description = "${api.response-codes.notFound.desc}", content = {
                    @Content(examples = { @ExampleObject(value = "") }) }) })
    



    @GetMapping("/business/get-list")
    public ResponseEntity<List<Business>> getUserList() {
        try {
            List<Business> users = new ArrayList<Business>();
            users = businessService.getUserList();

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }    
    



    @GetMapping("/business/{id}")
    public ResponseEntity<Business> getUserById(@PathVariable("id") Long id){
        return new ResponseEntity<Business>(businessService.getUserbyId(id), HttpStatus.OK);
    }



    
    @PostMapping("/business/save")
    public ResponseEntity<Business> saveBusiness(@RequestBody Business business){
        try{
            Business savedBusiness = businessService.createBusiness(business);
            
            return new ResponseEntity<>(savedBusiness, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @PostMapping("/business/authenticate") 
    public ResponseEntity<Business> authenticate (@RequestBody FormData formData){
        try{
            Business authenticatedBusiness = businessService.getUserbyEmail(formData.getEmail(),formData.getPassword());  
            System.out.print(authenticatedBusiness);
            return new ResponseEntity<>(authenticatedBusiness, HttpStatus.OK);     
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    
    @PutMapping("/business/update")
    public ResponseEntity<Business> updateMember(@RequestBody Business business) {
        // logger.info("Update new member");

        try {
            Business savedBusiness = businessService.updateBusiness(business);

            return new ResponseEntity<>(savedBusiness, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
;
