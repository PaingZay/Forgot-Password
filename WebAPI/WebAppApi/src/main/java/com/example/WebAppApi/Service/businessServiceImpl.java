package com.example.WebAppApi.Service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.WebAppApi.Model.Business;
import com.example.WebAppApi.Repository.BusinessRepository;


@Service
public class BusinessServiceImpl implements BusinessService {

    @Resource
    private BusinessRepository businessRepository;

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
}
