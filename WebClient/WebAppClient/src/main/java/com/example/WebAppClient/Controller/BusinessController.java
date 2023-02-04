package com.example.WebAppClient.Controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;
import com.example.WebAppClient.Model.Business;
import com.example.WebAppClient.Model.FoodWastePackage;
import com.example.WebAppClient.Model.Item;
import com.example.WebAppClient.Service.BusinessService;

@RequestMapping("/business")
@Controller
public class BusinessController {

    @Autowired
    BusinessService businessService;


    @Autowired
    WebClient webClient;   


    ////////// Dashboard and Request Collection ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/dashboard")                                                                  
    public String viewDashboard(@ModelAttribute ("collection") FoodWastePackage collection, Model model) {
        // List<Item> items = new ArrayList<>();
        // Item item1 = new Item ("Cheese Bread","Bread",1, "");
        // Item item2 = new Item ("Chocolate Bread","Bread",1, "");
        // Item item3 = new Item ("Latte","Coffee",3, "");
        // items.add(item1);
        // items.add(item2);
        // items.add(item3);
        // model.addAttribute("items",items);
        return "dashboard";
    }



    ////////// Profile ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/profile")                                                                  
    public String viewProfile(@ModelAttribute ("business") Business business, Model model) {

        String openingDays = "Mon, Tue, Wed, Thur, Fri, Sat, Sun";
        LocalTime openingTime =  LocalTime.of(10,00,00);
        LocalTime closingTime =  LocalTime.of(16,00,00);
        Business businessapi = new Business("breadtalk.amk@gmail.com","asdf","BreadTalk","Singapore","Bakery","Ang Mo Kio Hub", "123456" , "90844877", openingDays, openingTime, closingTime);
        model.addAttribute ("business",businessapi);
        return "profile";
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
}
