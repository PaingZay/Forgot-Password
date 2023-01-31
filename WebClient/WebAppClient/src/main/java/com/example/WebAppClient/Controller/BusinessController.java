package com.example.WebAppClient.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.WebAppClient.Model.Business;
import com.example.WebAppClient.Service.BusinessService;

@Controller
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @Autowired
    WebClient webClient;

    @GetMapping("/business")                                                                  
    public String viewAllUsers(Model model) {
        List <Business> allusers = businessService.getUserList();
        System.out.println (allusers.size());
        return "menu";
    }




    //Testing
    @GetMapping("/home")
    @ResponseBody                                                                   //use ResponseBody to send data in json format while using normal controller
    public ArrayList<String> viewHomePage(Model model) {
        return new ArrayList<String>();
    }

    //Testing Bert's cloud api
    @GetMapping("/")
    @ResponseBody
    public String getCallAPI(){
        return webClient.get()
                        .uri("/")                                               //endpoint name of api
                        .retrieve()
                        .bodyToMono(String.class)
                        .block(); 
    }
}
