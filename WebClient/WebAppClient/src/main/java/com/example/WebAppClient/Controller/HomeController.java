package com.example.WebAppClient.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.WebAppClient.DTO.FormData;
import com.example.WebAppClient.Model.Business;
import com.example.WebAppClient.Model.FoodWastePackage;
import com.example.WebAppClient.Model.Item;
import com.example.WebAppClient.Service.BusinessService;
import com.example.WebAppClient.Service.HomeService;


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

    @Autowired
    BusinessService businessService;



    ////////// Login and Logout ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    @GetMapping("/login")
    public String loginPre(@ModelAttribute FormData formData){
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute FormData formData, HttpSession session, Model model) {
        Business business = homeService.authenticate(formData);
        if(business!=null) {
         session.setAttribute("business", business); //set session
         FoodWastePackage foodwastepackage = new FoodWastePackage();
         model.addAttribute("foodwastepackage",foodwastepackage);

         List<Item> items = new ArrayList<>();
         Item item1 = new Item ("Cheese Bread","Bread",1, "");
         Item item2 = new Item ("Chocolate Bread","Bread",1, "");
         Item item3 = new Item ("Latte","Coffee",3, "");
         items.add(item1);
         items.add(item2);
         items.add(item3);
         model.addAttribute("items",items);
         return "redirect:/business/dashboard";
        }
    return "login";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session, @ModelAttribute FormData formData) {
    	session.invalidate();
        return "redirect:/login";
    }


    ////////// Registration ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    

    @GetMapping("/register")
    public String registerPage(@ModelAttribute ("business") Business business){
        return "registration";
    }

    @PostMapping("/register")
    public String registerBusiness(@ModelAttribute("business") Business business, @RequestParam("openingDays") String[] openingDays){
        System.out.println(openingDays);
        String joinedStrArr = String.join(",", openingDays);
    	business.setOpeningDays(joinedStrArr);
        businessService.create(business);
        
        return "registerSuccess";
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
