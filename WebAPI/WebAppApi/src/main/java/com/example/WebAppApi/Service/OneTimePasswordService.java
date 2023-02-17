package com.example.WebAppApi.Service;

import java.time.LocalTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebAppApi.Model.Business;
import com.example.WebAppApi.Model.OneTimePassword;
import com.example.WebAppApi.Repository.OneTimePasswordRepository;

@Service
public class OneTimePasswordService {

    private final Long expiryInterval = 5L * 60 * 1000;

    OneTimePasswordRepository oneTimePasswordRepository;

    OneTimePasswordHelpService oneTimePasswordHelpService;

    @Autowired
    public OneTimePasswordService(OneTimePasswordRepository oneTimePasswordRepository) {
        this.oneTimePasswordRepository = oneTimePasswordRepository;
    }

    public OneTimePassword returnOneTimePassword(String email) {
        OneTimePassword oneTimePassword = new OneTimePassword();

        oneTimePassword.setOneTimePasswordCode(oneTimePasswordHelpService.createRandomOneTimePassword().get());
        oneTimePassword.setExpires(new Date(System.currentTimeMillis() + expiryInterval));

        String openingDays = "Mon , Tue, Wed, Thur, Fri, Sat, Sun";
	 	LocalTime openingTime =  LocalTime.of(10,00,00);
	 	LocalTime closingTime =  LocalTime .of(16,00,00);
        oneTimePassword.setEmail(email);
        
        oneTimePasswordRepository.save(oneTimePassword);
        return oneTimePassword;
    }
}