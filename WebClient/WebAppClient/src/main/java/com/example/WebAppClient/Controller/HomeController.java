package com.example.WebAppClient.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class HomeController {

    @Autowired
    WebClient webClient;

    @GetMapping("/menu")                                                                  
    public String MenuPage() {
        return "menu";
    }

    @GetMapping("/login")
    public String loginPage () {
        return "login";
    }

    @GetMapping("/register")
    public String RegistrationPage () {
        return "registration";
    }
    
}
