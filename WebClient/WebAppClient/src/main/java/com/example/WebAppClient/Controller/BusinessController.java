package com.example.WebAppClient.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.WebAppClient.DTO.RequestCollectionForm;
import com.example.WebAppClient.Model.Business;
import com.example.WebAppClient.Model.FoodWastePackage;
import com.example.WebAppClient.Model.Item;
import com.example.WebAppClient.Service.BusinessService;
import com.example.WebAppClient.Service.FoodWastePackageService;
import com.example.WebAppClient.Validator.BusinessValidator;

@RequestMapping("/business")
@Controller
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @Autowired
    FoodWastePackageService fwpService;


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
    public String viewDashboard(@ModelAttribute ("requestcollectionform") RequestCollectionForm requestcollectionform, Model model) {
        // List<Item> items = new ArrayList<>();
        // Item item1 = new Item ("Cheese Bread","Bread",1, "");
        // Item item2 = new Item ("Chocolate Bread","Bread",1, "");
        // Item item3 = new Item ("Latte","Coffee",3, "");
        // items.add(item1);
        // items.add(item2);
        // items.add(item3);
        
        List<FoodWastePackage> requestList = fwpService.getPendingList();
        model.addAttribute("pending", requestList);
        
        List<FoodWastePackage> historyList = fwpService.getHistoryList();
        model.addAttribute("history", historyList);

        String[] items = {"Cheese Cake", "Chocolate Bread", "Latte"};
        model.addAttribute("items",items);

        return "dashboard";
    }


    @GetMapping("/dashboard/details/{id}")
	public String viewDetails(@PathVariable int id, Model model)
    {
        System.out.print("HELLO CLASS");

        RequestCollectionForm rcf = new RequestCollectionForm();
        FoodWastePackage fwp = fwpService.getPackagebyId(id);

        rcf.setId(fwp.getId());
        rcf.setPackageName(fwp.getPackageName());
        rcf.setQuantity(fwp.getQuantity());
        rcf.setStart(fwp.getStart());
        rcf.setEnd(fwp.getEnd());
        rcf.setPickUpDate(fwp.getPickUpDate());
        rcf.setStatus(fwp.getStatus());
        rcf.setDescription(fwp.getDescription());
        rcf.setCategory(fwp.getCategory());
        rcf.setItemList(fwp.getItemList());
        rcf.setBusinessId(fwp.getBusiness().getId());
        
        model.addAttribute("details", rcf);
        return "collectionDetails";
    }

    @PostMapping("/dashboard/createrequest")
    public String createRequest(@Valid @ModelAttribute("requestcollectionform") RequestCollectionForm requestCollectionForm, 
                                @RequestParam("itemList") String[] itemList, @RequestParam("itemQuantity") Integer[] itemQuantity, BindingResult result, Model model, HttpSession session)
    {

        System.out.print("Item List"+ itemList);

        Business b1 = (Business) session.getAttribute("business");
        Long bId = b1.getId();

        String itemQ = "";
        for (int i = 0; i < itemList.length ; i++){
            String quantity = String.valueOf(itemQuantity[i]);
            itemQ += itemList[i] + " [" + quantity + "] , ";
        }

        // String joinedStrArr = String.join(", ", itemList);
        requestCollectionForm.setItemList(itemQ);    //Ideally should retrieve from RequestCollecitonForm
        requestCollectionForm.setBusinessId(bId);
        
        System.out.println("Complete");
        System.out.println(requestCollectionForm.toString());
        fwpService.createPackage(requestCollectionForm);
        
        return "redirect:/business/dashboard";
    }



    @GetMapping("/dashboard/collected/{id}")
    public String setCollectedStatus(@PathVariable int id, Model model)
    {
        fwpService.updatePackage(id);

        return "redirect:/business/dashboard";
    }

    @GetMapping("/dashboard/cancelled/{id}")
    public String setCancelledStatus(@PathVariable int id, Model model)
    {
        fwpService.updateCancelled(id);
        return "redirect:/business/dashboard";
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
