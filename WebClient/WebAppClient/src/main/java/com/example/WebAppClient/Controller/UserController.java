package com.example.WebAppClient.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.WebAppClient.Model.User;
import com.example.WebAppClient.Service.UserService;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    WebClient webClient;

    @GetMapping("/users")                                                                  
    public String viewAllUsers(Model model) {
        List <User> allusers = userService.getUserList();
        System.out.println (allusers.size());
        return "index";
    }




    //Testing
    @GetMapping("/users")
    @ResponseBody                                                                   //use ResponseBody to send data in json format while using normal controller
    public ArrayList<String> viewHomePage(Model model) {
        // List <User> allusers = userService.getUserList();
        // System.out.println (allusers.size());
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
