package com.example.WebAppClient.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.WebAppClient.Model.FormData;
import com.example.WebAppClient.Service.HomeService;

@Controller
public class HomeController {

    @Autowired
    HomeService homeService;

    @GetMapping("/menu")                                                                  
    public String MenuPage() {
        return "menu";
    }

    @GetMapping("/login")
    public String loginPre(@ModelAttribute FormData formData){
    return "login";
    }
     @PostMapping("/login")
    public String loginPost(@ModelAttribute FormData formData) {
        if(homeService.authenticate(formData.getEmail(),formData.getPassword())) {
         return "dashboard";
    }
    return "login";
    }

    
}
