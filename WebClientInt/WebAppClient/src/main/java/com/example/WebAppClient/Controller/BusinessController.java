package com.example.WebAppClient.Controller;

import java.util.ArrayList;

import java.util.List;
import java.time.LocalDate;
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

import com.example.WebAppClient.Model.Business;
import com.example.WebAppClient.Model.FoodWastePackage;
import com.example.WebAppClient.Model.Item;
import com.example.WebAppClient.Service.Interface.BusinessService;
import com.example.WebAppClient.Service.Interface.FoodWastePackageService;
import com.example.WebAppClient.Service.Interface.ItemService;
import com.example.WebAppClient.Validator.BusinessValidator;

@RequestMapping("/business")
@Controller
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @Autowired
    FoodWastePackageService fwpService;

    @Autowired
    ItemService itemService;


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
    public String viewDashboard(@ModelAttribute ("foodWastePackage") FoodWastePackage foodWastePackage, Model model, HttpSession session) {
        
        Business business = (Business) session.getAttribute("business");

        List<Item> itemList = itemService.getItemListByBizId(business.getId());
        // List<Item> itemList = itemService.getItemListByBizId();
        String[] items = new String[itemList.size()];
        for (int i = 0; i < itemList.size(); i++){
            items[i] = itemList.get(i).getName();
        }

        model.addAttribute("businessinfo", business);
        LocalDate today = LocalDate.now();
        model.addAttribute("today", today);
        model.addAttribute("tomorrow", today.plusDays(1));

        List<FoodWastePackage> requestList = fwpService.getPendingList(business.getId());
        if(requestList != null)
        {
            model.addAttribute("pending", requestList);
        }
        
        List<FoodWastePackage> historyList = fwpService.getHistoryList(business.getId());
        if(historyList != null)
        {
            model.addAttribute("history", historyList);
        }
        model.addAttribute("items",items);

        return "dashboard";
    }


   @GetMapping("/dashboard/details/{id}")
	public String viewDetails(@PathVariable Long id, Model model)
    {
       System.out.print("HELLO CLASS");
       FoodWastePackage fwp = fwpService.getPackagebyId(id);
       
       model.addAttribute("details", fwp);
       return "collectionDetails";
    }
   

    @GetMapping("/dashboard/remove/{id}")
	public String removeHistory(@PathVariable Long id, Model model)
    {

        fwpService.removeHistoryById(id);
        
        return "redirect:/business/dashboard";
    }

    @GetMapping("/dashboard/removeall/{id}")
	public String removeAllHistory(@PathVariable Long id, Model model)
    {
    	System.out.println("Removing");
    	System.out.println(id);

        fwpService.removeAllHistoryByBizId(id);
        
        return "redirect:/business/dashboard";
    }

    // @PostMapping("/dashboard/createrequest")
    // public String createRequest(@Valid @ModelAttribute("requestcollectionform") RequestCollectionForm requestCollectionForm, 
    //                             @RequestParam("itemList") String[] itemList, @RequestParam("itemQuantity") Integer[] itemQuantity, BindingResult result, Model model, HttpSession session)
    // {

    //     System.out.print("Item List"+ itemList);

    //     Business b1 = (Business) session.getAttribute("business");
    //     Long bId = b1.getId();

    //     String itemQ = "";
    //     for (int i = 0; i < itemList.length ; i++){
    //         String quantity = String.valueOf(itemQuantity[i]);
    //         itemQ += itemList[i] + " [" + quantity + "] , ";
    //     }

    //     // String joinedStrArr = String.join(", ", itemList);
    //     requestCollectionForm.setItemList(itemQ);    //Ideally should retrieve from RequestCollecitonForm
    //     requestCollectionForm.setBusinessId(bId);
        
    //     System.out.println("Complete");
    //     System.out.println(requestCollectionForm.toString());
    //     fwpService.createPackage(requestCollectionForm);
        
    //     return "redirect:/business/dashboard";
    // }

    @PostMapping("/dashboard/createrequest")
    public String createRequest(@Valid @ModelAttribute("foodwastepackage") FoodWastePackage foodWastePackage, 
                                @RequestParam("itemList") String[] itemList, @RequestParam("itemQuantity") Integer[] itemQuantity, BindingResult result, Model model, HttpSession session)
    {
        System.out.println("Creating Request");
        System.out.print("Item List"+ itemList);

        Business b1 = (Business) session.getAttribute("business");

        String itemQ = "";
        for (int i = 0; i < itemList.length ; i++){
            String quantity = String.valueOf(itemQuantity[i]);
            itemQ += itemList[i] + " [ Qty: " + quantity + "] , ";
        }

        // String joinedStrArr = String.join(", ", itemList);
        foodWastePackage.setItemList(itemQ);    //Ideally should retrieve from RequestCollecitonForm
        foodWastePackage.setBusiness(b1);
        fwpService.createPackage(foodWastePackage);

        return "redirect:/business/dashboard";
    }


    @GetMapping("/dashboard/collected/{id}")
    public String setCollectedStatus(@PathVariable Long id, Model model)
    {
        fwpService.updateCollected(id);

        return "redirect:/business/dashboard";
    }

    @GetMapping("/dashboard/cancelled/{id}")
    public String setCancelledStatus(@PathVariable Long id, Model model)
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


    
    ////////// Update Profile ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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
      public String editProfile(@ModelAttribute Business business, HttpSession session, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
  
          Business b1 = (Business) session.getAttribute("business");
  
          System.out.println("Old password and new Password" + oldPassword + newPassword);
  
          if(!oldPassword.trim().isEmpty() && !newPassword.trim().isEmpty()) {
              if(b1.getPassword().equals(oldPassword)){
                  business.setPassword(newPassword);
                  System.out.println("Successfully Updated Password");
              }
              else{
                  System.out.println("Update password failed");
                  return "redirect:/business/update";
              }
          }
          else if(oldPassword.trim().isEmpty() && !newPassword.trim().isEmpty())
          {
              System.out.println("Old password is empty");
              return "redirect:/business/update";//Ideally show message
          }
          else if(!oldPassword.trim().isEmpty() && newPassword.trim().isEmpty())
          {
              System.out.print("New password is null");
              business.setPassword(b1.getPassword());
          }
          else
          {
              System.out.println("Both textbox is empty");
              business.setPassword(b1.getPassword());
          }
  
          business.setId (b1.getId());
          business.setBranch(b1.getBranch());
          business.setBusinessType(b1.getBusinessType());
          Business newb = businessService.updateBusiness(business);
          return "redirect:/business/profile";
      }
    
    
    ////////// Delete Profile ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    

    @GetMapping("/delete/{id}")
    public String deleteThroughId(@PathVariable(value = "id") Long id) {
        businessService.deleteBusiness(id);

        return "menu";
    }


    ////////// Items Menu  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/itemlist")                                                                  
    public String viewDashboard(@ModelAttribute ("requestcollectionform") Item item, Model model, HttpSession session) {
        Business b1 = (Business) session.getAttribute("business");

        List<Item> itemList = itemService.getItemListByBizId(b1.getId());
        // List<Item> itemList = itemService.getItemListByBizId();

        model.addAttribute("itemList", itemList);

        Item emptyItem = new Item();
        
        model.addAttribute("emptyItem", emptyItem);

        return "itemsMenu";
    }
    
    @PostMapping("/itemlist/add")
    public String createItem(@Valid @ModelAttribute("requestcollectionform") Item item, BindingResult result, Model model, HttpSession session)
    {   
        Business b1 = (Business) session.getAttribute("business");
        item.setBusiness(b1);
        
        // Item existedItem = itemService.getItemByName(item.getName());
        // if(existedItem == null)
        // {
          itemService.createItem(item);
        // }
        // else
        // {
        //   System.out.println("Your item already existed!");
        // }
         
        return "redirect:/business/itemlist";
    }
    
    @GetMapping("/item/remove/{id}")
    public String removeItemThroughId(@PathVariable(value = "id") Long id) {
        itemService.deleteItem(id);
        return "redirect:/business/itemlist";
    }



    @GetMapping("/item/update/{id}")
    public String updateItemPage(@PathVariable Long id, Model model) {
        
        model.addAttribute("item", itemService.getItemById(id));
        return "editItem";
    }

    @PostMapping("/item/update/{id}")
    public String updatedItem(@ModelAttribute Item item, @PathVariable Long id, HttpSession session){
        Item newItem = new Item();
        
        Business b1 = (Business) session.getAttribute("business");
        item.setBusiness(b1);
        
        newItem.setId(id);
        newItem.setBusiness(item.getBusiness());
        newItem.setCategory(item.getCategory());
        newItem.setId(id);
        newItem.setName(item.getName());
        newItem.setWeight(item.getWeight());
        newItem.setManufacturer(item.getManufacturer());
        newItem.setPeriod(item.getPeriod());

        System.out.println("new item");
        System.out.println (newItem.getBusiness());

        Item updatedItem = itemService.updateItem(newItem);
        return "redirect:/business/itemlist";
    }
}
