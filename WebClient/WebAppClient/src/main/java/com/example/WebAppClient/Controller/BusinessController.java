package com.example.WebAppClient.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import com.example.WebAppClient.Model.Business;
import com.example.WebAppClient.Model.FoodWastePackage;
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
        return "dashboard";
    }



    ////////// Profile ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/profile")                                                                  
    public String viewProfile(@ModelAttribute ("business") Business business, Model model) {  
        model.addAttribute ("business",businessService.getUserbyId(1L));
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
