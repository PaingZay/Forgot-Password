package com.example.WebAppClient.Controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;
import com.example.WebAppClient.Model.Business;
import com.example.WebAppClient.Model.OpeningDays;
import com.example.WebAppClient.Service.BusinessService;

@Controller
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @Autowired
    WebClient webClient;

    String [] days = {"Mon","Tue","Wed","Thur","Fri","Sat","Sun"};

    @GetMapping("/business")                                                                  
    public String viewAllUsers(Model model) {
        List <Business> allusers = businessService.getUserList();
        return "menu";
    }


    @GetMapping("/register")
    public String registerPage(@ModelAttribute ("business") Business business, Model model){
        List<OpeningDays> days = new ArrayList<>();
        OpeningDays d1 = new OpeningDays("Monday");
        OpeningDays d2 = new OpeningDays("Tuesday");
        OpeningDays d3 = new OpeningDays("Wednesday");
        OpeningDays d4 = new OpeningDays("Thursday");
        OpeningDays d5 = new OpeningDays("Friday");
        OpeningDays d6= new OpeningDays("Saturday");
        OpeningDays d7 = new OpeningDays("Sunday");
        days.add(d1);
        days.add(d2);
        days.add(d3);
        days.add(d4);
        days.add(d5);
        days.add(d6);
        days.add(d7);
        model.addAttribute("days",days);
        return "registration";
    }

    @PostMapping("/register")
    public String registerBusiness(@ModelAttribute("business") Business business, String[]days){
        
        //Not related to this project but this is how to save a value to different object
        //List<Role> selectedRole = new ArrayList<Role>();
        //selectedRole.add(memberForm.getRole());
        // String[] openingDays = {"Mon", "Tue", "Wed", "Thur", "Fri", "Sat", "Sun"};
        // LocalTime openingTime =  LocalTime.of(10,00,00);
        // LocalTime closingTime =  LocalTime.of(16,00,00);
        // Business businessapi  = new Business("breadtalk.amk@gmail.com","asdf","Corporate User","BreadTalk","Singapore","Bakery","Ang Mo Kio Hub", "123456" , "90844877", openingDays, openingTime, closingTime);
        business.setOpeningDays(days);
        businessService.create(business);
        String[]openingDays = business.getOpeningDays();
        for (int i = 0; i < openingDays.length; i++)
        {
            System.out.println (openingDays[i]);
        }
        return "dashboard";
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
