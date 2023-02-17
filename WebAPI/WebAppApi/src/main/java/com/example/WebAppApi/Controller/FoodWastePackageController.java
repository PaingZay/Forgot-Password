package com.example.WebAppApi.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebAppApi.Model.FoodWastePackage;
import com.example.WebAppApi.Service.BusinessService;
import com.example.WebAppApi.Service.FoodWastePackageService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


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


    

    @PostMapping("/foodwastepackage/save")
    public ResponseEntity<FoodWastePackage> savePackage(@RequestBody FoodWastePackage foodWastePackage){
        try{

            FoodWastePackage savedPackage= foodwastepackageService.createPackage(foodWastePackage);
            return new ResponseEntity<>(savedPackage, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/foodwastepackage/get-list-pending/{id}")
    public ResponseEntity<List<FoodWastePackage>> getPendingList(@PathVariable("id") Long id) {
        try {
            List<FoodWastePackage> packages = new ArrayList<FoodWastePackage>();
            packages = foodwastepackageService.getPendingList(id);

            if (packages.isEmpty()) {
                List<FoodWastePackage> emptypackage = new ArrayList<FoodWastePackage>();
                return new ResponseEntity<>(emptypackage, HttpStatus.OK);
            }
            return new ResponseEntity<>(packages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/foodwastepackage/get-list-history/{id}")
    public ResponseEntity<List<FoodWastePackage>> getHistory(@PathVariable("id") Long id) {
        try {
            List<FoodWastePackage> packages = new ArrayList<FoodWastePackage>();
            packages = foodwastepackageService.getHistoryList(id);

            if (packages.isEmpty()) {
                List<FoodWastePackage> emptypackage = new ArrayList<FoodWastePackage>();
                return new ResponseEntity<>(emptypackage, HttpStatus.OK);
            }
            return new ResponseEntity<>(packages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PutMapping("/foodwastepackage/update/collected/{id}")
    public ResponseEntity<FoodWastePackage> updateMember(@RequestBody Long id) {

        try {
            
            FoodWastePackage savedPackage = foodwastepackageService.updatePackage(id);

            System.out.println("This is new package from controller");
            System.out.println("This is saved package" + savedPackage.toString());

            return new ResponseEntity<>(savedPackage, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/foodwastepackage/update/cancelled/{id}")
    public ResponseEntity<FoodWastePackage> updateCancelledStatus(@RequestBody Long id) {

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
    public ResponseEntity<FoodWastePackage> getPackageById(@PathVariable("id") Long id){

        
        FoodWastePackage fwp = foodwastepackageService.getPackageById(id);
        System.out.print("This is object from API"+fwp.toString());
        return new ResponseEntity<FoodWastePackage>(foodwastepackageService.getPackageById(id), HttpStatus.OK);
    }

    /////////////////////////////////////////////Remove History by Using Id//////////////////////////////////////

    @DeleteMapping("/foodwastepackage/history/remove/{id}")
    public ResponseEntity<Long> removeHistory(@PathVariable("id") Long id) {
        try {
            var isRemoved = foodwastepackageService.removeHistoryById(id);

            if (!isRemoved) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/foodwastepackage/history/remove-all/{id}")
    public ResponseEntity<Long> removeAllHistory(@PathVariable Long id ) {
        try {
            var isRemoved = foodwastepackageService.removeAllHistoryById(id);

            if (!isRemoved) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
;
