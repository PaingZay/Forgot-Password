package com.example.WebAppClient.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import com.example.WebAppClient.Model.Business;
import com.example.WebAppClient.Model.FoodWastePackage;
import com.example.WebAppClient.Model.Item;
import com.example.WebAppClient.Service.BusinessService;
import com.example.WebAppClient.Validator.BusinessValidator;

@RequestMapping("/business")
@Controller
public class BusinessController {

    @Autowired
    BusinessService businessService;


    @Autowired
    WebClient webClient;  
    
    @Autowired
    BusinessValidator businessValidator;

    
	@InitBinder("business")
	private void initEmployeeBinder(WebDataBinder binder) {
	  binder.addValidators(businessValidator);
	}	  

    ////////// Dashboard and Request Collection ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @GetMapping("/dashboard")                                                                  
    public String viewDashboard(@ModelAttribute ("foodwastepackage") FoodWastePackage foodwastepackage, Model model) {
        List<Item> items = new ArrayList<>();
        Item item1 = new Item ("Cheese Bread","Bread",1, "");
        Item item2 = new Item ("Chocolate Bread","Bread",1, "");
        Item item3 = new Item ("Latte","Coffee",3, "");
        items.add(item1);
        items.add(item2);
        items.add(item3);
        model.addAttribute("items",items);

        return "dashboard";
    }



    ////////// Profile ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @GetMapping("/profile")                                                                  
    public String viewProfile(@ModelAttribute ("business") Business business, Model model, HttpSession session) {  
        Business b1 = (Business) session.getAttribute("business");
        Long bId = b1.getId();
        model.addAttribute ("business",businessService.getUserbyId(bId));
        return "profile";
    }


    
    ////////// Update ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @GetMapping("/update")
    public String editProfilePage(Model model, HttpSession session) {
        Business b1 = (Business) session.getAttribute("business");
        System.out.println (b1);
        String openingDays = b1.getOpeningDays();
        model.addAttribute("business", b1);
        model.addAttribute ("openingDays",openingDays);
        return "editprofile";
      }
    
    @PostMapping("/update")
	public String editProfile(@ModelAttribute Business business, HttpSession session) {

        Business b1 = (Business) session.getAttribute("business");
        business.setId (b1.getId());
        business.setPassword(b1.getPassword());
        business.setBranch(b1.getBranch());
        business.setBusinessType(b1.getBusinessType());
        System.out.println (business);

		Business newb = businessService.updateBusiness(business);
        System.out.println(newb);

		return "redirect:/business/profile";
	}
    
    
    ////////// Delete ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    

    @GetMapping("/delete/{id}")
    public String deleteThroughId(@PathVariable(value = "id") Long id) {
        businessService.deleteBusiness(id);

        return "menu";
    }

}
