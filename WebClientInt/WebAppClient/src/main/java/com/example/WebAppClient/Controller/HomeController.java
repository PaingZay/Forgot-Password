package com.example.WebAppClient.Controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.WebAppClient.Model.OneTimePassword;
import com.example.WebAppClient.Service.Interface.EmailSenderService;
import com.example.WebAppClient.Service.Interface.BusinessService;
import com.example.WebAppClient.Service.Interface.HomeService;
import com.example.WebAppClient.DTO.FormData;
import com.example.WebAppClient.Model.Business;
import com.example.WebAppClient.Model.FoodWastePackage;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


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

    @Autowired
    private EmailSenderService emailSenderService;



    ////////// Login and Logout ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    @GetMapping("/login")
    public String loginPre(@ModelAttribute FormData formData){
        return "login";
    }

    
    @GetMapping("/otp")
    public String getOTP(@ModelAttribute("business") Business business)
    {
        return"otpverification";
    }


    @PostMapping("/otp")
    public String verify(@RequestParam("otpInput") int inputOtp, @ModelAttribute ("business") Business business, Model model){

        OneTimePassword retrievedOtp = homeService.retrieveOTP(business.getEmail());
        if(inputOtp == retrievedOtp.getOneTimePasswordCode() && business.getEmail().equals(retrievedOtp.getEmail()))
        {
            businessService.create(business);//After verification is done create Business
            return "registerSuccess";
        }
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute FormData formData, HttpSession session, Model model) {
        Business business = homeService.authenticate(formData);
        if(business!=null) {
         session.setAttribute("business", business); //set session
         FoodWastePackage foodwastepackage = new FoodWastePackage();
         model.addAttribute("foodwastepackage",foodwastepackage);

         return "redirect:/business/dashboard";
        }
    model.addAttribute("Invalid_login", "Incorrect Email or Password!");
    return "login";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session, @ModelAttribute FormData formData) {
    	session.invalidate();
        return "redirect:/login";
    }


    ////////// Registration ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    

    @GetMapping("/register")
    public String registerPage(@ModelAttribute Business business){
        return "registration";
    }

    @PostMapping("/register")
    public String registerBusiness(@Valid @ModelAttribute Business business, BindingResult result, @RequestParam("openingDays") String[] openingDays, Model model,RedirectAttributes redirectAttributes){


        if (result.hasErrors()) 
		{
		    return "registration";
		}

        String joinedStrArr = String.join(", ", openingDays);
    	business.setOpeningDays(joinedStrArr);

        

        // System.out.println("Please my email and otp" + generatedOtp.getEmail()+generatedOtp.getOneTimePasswordCode());
        OneTimePassword generatedOtp = homeService.generateOTP(business.getEmail());//Generate OTP and Save

        String toEmail = generatedOtp.getEmail();
        String otp = generatedOtp.getOneTimePasswordCode().toString();

        emailSenderService.sendSimpleEmail(toEmail, otp, "This is your OTP");

        redirectAttributes.addFlashAttribute("business", business);
        return "redirect:/otp";
    }

    @PostMapping("/resendotp")
    public String resendOTP(@RequestParam("resendEmailInput") String email){

        OneTimePassword generatedOtp = homeService.generateOTP(email);//Generate OTP and Save
        String toEmail = email;
        String otp = generatedOtp.getOneTimePasswordCode().toString();
        emailSenderService.sendSimpleEmail(toEmail, otp, "FoodShare OTP Verification");

        return "redirect:/otp";
    }

    //////////////////////////////////FIND ACCOUNT BY EMAIL
    
    @GetMapping("/findaccount")
    public String findAccByEmail(){
        return "findaccount";
    }

    @PostMapping("/findaccount")
    public String findAccount(@RequestParam("emailInput") String email, @RequestParam("emailInput2") String email2, RedirectAttributes redirectAttributes){
        Business existingBusiness = businessService.getBusinessbyEmail(email);
        if(existingBusiness != null){
            System.out.print(existingBusiness);
            redirectAttributes.addFlashAttribute("business", existingBusiness);
            if(email2 != null){
                OneTimePassword generatedOtp = homeService.generateOTP(email);//Generate OTP and Save
                String toEmail = email;
                String otp = generatedOtp.getOneTimePasswordCode().toString();
                emailSenderService.sendSimpleEmail(toEmail, otp, "FoodShare OTP Verification");
                return "redirect:/forgotpassword-otp";
            }
            else{
                return "redirect:/findaccount";
            }
        }
        else{
            return "redirect:/login";
        }
    }

    @GetMapping("/forgotpassword-otp")
    public String getotpForgotPassword(){
        return "forgotpassword-otp";
    }

    @PostMapping("/forgotpassword-otp")
    public String otpForgotPassword(@RequestParam("otpInput") int inputOtp, @RequestParam("emailInput") String email){
        OneTimePassword retrievedOtp = homeService.retrieveOTP(email);
        if(inputOtp == retrievedOtp.getOneTimePasswordCode() && email.equals(retrievedOtp.getEmail()))
        {
            return "redirect:/forgotpassword-reset";
        }
        else
        {
        return "redirect:/login";
        }
    }

    @GetMapping("/forgotpassword-reset")
    public String getResetPassword(){
        return "resetpassword";
    }

    @PostMapping("/forgotpassword-reset")
    public String postResetPassword(@RequestParam("emailInput") String emailInput,@RequestParam("passwordInput") String passwordInput){

        FormData formdata = new FormData(emailInput, passwordInput);

        businessService.updatePasswordByEmail(formdata);

        return "redirect:/login";
    }

}
