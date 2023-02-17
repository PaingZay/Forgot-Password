package com.example.WebAppApi.Service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.WebAppApi.Model.Business;
import com.example.WebAppApi.Model.OneTimePassword;
import com.example.WebAppApi.Repository.BusinessRepository;
import com.example.WebAppApi.Repository.OneTimePasswordRepository;


@Service
public class BusinessServiceImpl implements BusinessService {

    @Resource
    private BusinessRepository businessRepository;

    @Resource
    private OneTimePasswordRepository otpRepository;

    @Override
    public Business createBusiness(Business business) {
        return businessRepository.saveAndFlush(business);
    }  

    @Override
    public List<Business> getUserList() {
        return businessRepository.findAll();
    }

    @Override 
    public Business getUserbyId (Long id) {
        return businessRepository.getById(id);
    }

    @Override 
    public Business getUserbyEmail (String email, String password) {
        return businessRepository.getByEmail(email,password);
    }

    @Override
    public Business updateBusiness (Business business){
        return businessRepository.saveAndFlush(business);
    }
    @Override 
    public OneTimePassword retrieveOTP (String email) {
        List<OneTimePassword> otps = otpRepository.getOTPByEmail(email);
        OneTimePassword lastotp = otps.get(0);
        System.out.println("LAST OTP"+lastotp.printout());
        return lastotp;
    }

    @Override
    public Business getBusinessbyEmail (String email) {
        return businessRepository.getBusinessbyEmail(email);
    }
    
}
