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

import com.example.WebAppApi.DTO.RequestCollectionForm;
import com.example.WebAppApi.Model.Business;
import com.example.WebAppApi.Model.FoodWastePackage;
import com.example.WebAppApi.Service.BusinessService;
import com.example.WebAppApi.Service.FoodWastePackageService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class FoodWastePackageController {

    @Autowired 
    private FoodWastePackageService foodwastepackageService;

    @Autowired 
    private BusinessService businessService;


    

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "${api.response-codes.ok.desc}"),
            @ApiResponse(responseCode = "400", description = "${api.response-codes.badRequest.desc}", content = {
                    @Content(examples = { @ExampleObject(value = "") }) }),
            @ApiResponse(responseCode = "404", description = "${api.response-codes.notFound.desc}", content = {
                    @Content(examples = { @ExampleObject(value = "") }) }) })
    



    @GetMapping("/foodwastepackage")
    public ResponseEntity<List<FoodWastePackage>> getAllPackages() {
        try {
            List<FoodWastePackage> packages = new ArrayList<FoodWastePackage>();
            packages = foodwastepackageService.getPackageList();

            if (packages.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(packages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }    


    

    @PostMapping("/foodwastepackage")
    public ResponseEntity<FoodWastePackage> savePackage(@RequestBody RequestCollectionForm requestcollectionform){
        try{
            FoodWastePackage newPackage = new FoodWastePackage();
            newPackage.setPackageName(requestcollectionform.getPackageName());
            newPackage.setQuantity(requestcollectionform.getQuantity());
            newPackage.setStart(requestcollectionform.getStart());
            newPackage.setEnd(requestcollectionform.getEnd());
            newPackage.setPickUpDate(requestcollectionform.getPickUpDate());
            newPackage.setDescription(requestcollectionform.getDescription());
            newPackage.setCategory(requestcollectionform.getCategory());
            newPackage.setItemList(requestcollectionform.getItemList());
            Business business = businessService.getUserbyId(requestcollectionform.getBusinessId());
            newPackage.setBusiness(business);

            FoodWastePackage savedPackage= foodwastepackageService.createPackage(newPackage);
            return new ResponseEntity<>(savedPackage, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/foodwastepackage/pending")
    public ResponseEntity<List<FoodWastePackage>> getPendingList() {
        try {
            List<FoodWastePackage> packages = new ArrayList<FoodWastePackage>();
            packages = foodwastepackageService.getPendingList();

            if (packages.isEmpty()) {
                List<FoodWastePackage> emptypackage = new ArrayList<FoodWastePackage>();
                return new ResponseEntity<>(emptypackage, HttpStatus.OK);
            }
            return new ResponseEntity<>(packages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/foodwastepackage/history")
    public ResponseEntity<List<FoodWastePackage>> getHistory() {
        try {
            List<FoodWastePackage> packages = new ArrayList<FoodWastePackage>();
            packages = foodwastepackageService.getHistoryList();

            if (packages.isEmpty()) {
                List<FoodWastePackage> emptypackage = new ArrayList<FoodWastePackage>();
                return new ResponseEntity<>(emptypackage, HttpStatus.OK);
            }
            return new ResponseEntity<>(packages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/foodwastepackage/collected")
    public ResponseEntity<FoodWastePackage> updateMember(@RequestBody int id) {

        try {
            
            FoodWastePackage savedPackage = foodwastepackageService.updatePackage(id);

            System.out.println("This is new package from controller");
            System.out.println("This is saved package" + savedPackage.toString());

            return new ResponseEntity<>(savedPackage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/foodwastepackage/cancelled")
    public ResponseEntity<FoodWastePackage> updateCancelledStatus(@RequestBody int id) {

        try {
            
            FoodWastePackage savedPackage = foodwastepackageService.updateCancelled(id);

            System.out.println("This is new package from controller");
            System.out.println("This is saved package" + savedPackage.toString());

            return new ResponseEntity<>(savedPackage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/foodwastepackage/retrieve/{id}")
    public ResponseEntity<FoodWastePackage> getPackageById(@PathVariable("id") int id){

        
        FoodWastePackage fwp = foodwastepackageService.getPackageById(id);
        System.out.print("This is object from API"+fwp.toString());
        return new ResponseEntity<FoodWastePackage>(foodwastepackageService.getPackageById(id), HttpStatus.OK);
    }
}
;
