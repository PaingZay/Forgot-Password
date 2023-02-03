package com.example.WebAppClient.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.WebAppClient.Model.Dummy;
import com.example.WebAppClient.Model.FormData;
import com.example.WebAppClient.Service.HomeService;

import reactor.core.publisher.Mono;

@Controller
public class HomeController {

    @Autowired
    HomeService homeService;

    @Autowired
    WebClient webClient;  

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








    //Cloud API Testing 

    // @GetMapping("/dummyregister")
    // public String registerPage(@ModelAttribute ("formData") FormData formData){
    //     return "fakeregister";
    // }

    // @PostMapping("/dummyregister")
    // public String fakeRegister (@ModelAttribute ("formData") FormData formData){

    //     Dummy returnDummy = webClient.post().uri("/api/dummy")
    //                                  .body(Mono.just(formData), FormData.class)
    //                                  .retrieve()
    //                                  .bodyToMono (Dummy.class)
    //                                  .block();
    //     if ((returnDummy.getEmail())!=null)
    //     {
    //         return "fakedashboard";   
    //     }  
    //     return "fakeregister";  
    // }

    // @GetMapping("/authenticate")
    // public String authenPage(@ModelAttribute FormData formData){
    //     return "fakelogin";
    // }

    // @PostMapping("/authenticate")
    // public String authen (@ModelAttribute FormData formData){

    //     Dummy business = webClient.post().uri("/api/dummy/authenticate")
    //                                  .body(Mono.just(formData), FormData.class)
    //                                  .retrieve()
    //                                  .bodyToMono (Dummy.class)
    //                                  .block();
    //     if ((business.getEmail())!=null)
    //     {
    //         return "fakedashboard";   
    //     }  
    //     return "fakelogin";  
    // }


    // @GetMapping("/home")
    // @ResponseBody                                                                   //use ResponseBody to send data in json format while using normal controller
    // public ArrayList<String> viewHomePage(Model model) {
    //     return new ArrayList<String>();
    // }

    // //Testing Bert's cloud api
    // @GetMapping("/")
    // @ResponseBody
    // public String getCallAPI(){
    //     return webClient.get()
    //                     .uri("/")                                               //endpoint name of api
    //                     .retrieve()
    //                     .bodyToMono(String.class)
    //                     .block(); 
    // }
}
