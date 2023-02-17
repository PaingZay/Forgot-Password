package com.example.WebAppApi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.WebAppApi.Model.OneTimePassword;
import com.example.WebAppApi.Service.BusinessService;
import com.example.WebAppApi.Service.OneTimePasswordService;

@RestController
@RequestMapping("/api")
public class OneTimePasswordController {

    @Autowired
    private OneTimePasswordService oneTimePAsswordService;

    @Autowired
    private BusinessService businessService;

    public OneTimePasswordController(OneTimePasswordService oneTimePAsswordService) {
        this.oneTimePAsswordService = oneTimePAsswordService;
    }

    @PostMapping("/generateOTP/{email}")
    @ResponseBody
    private Object getOneTimePassword(@PathVariable("email") String email) {
        try {
            return ResponseEntity.ok(oneTimePAsswordService.returnOneTimePassword(email));
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }

    /////////////////////////////////For OTP///////////////////////////////////////////////////

    @GetMapping("/OTP/retrieve/{email}")
    public ResponseEntity<OneTimePassword> getOTPByEmail(@PathVariable("email") String email){
        OneTimePassword otp = businessService.retrieveOTP(email);
        System.out.print("This is object from API"+otp.printout());
        return new ResponseEntity<OneTimePassword>(businessService.retrieveOTP(email), HttpStatus.OK);
    }
}