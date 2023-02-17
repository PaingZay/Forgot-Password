package com.example.WebAppApi.Service;

import java.util.List;

import com.example.WebAppApi.DTO.FormData;
import com.example.WebAppApi.Model.Business;
import com.example.WebAppApi.Model.OneTimePassword;



public interface BusinessService {

    Business createBusiness (Business business);
    List<Business> getUserList ();
    Business getUserbyId (Long id);
    Business getUserbyEmail (String email, String password);
    Business updateBusiness (Business business);
    OneTimePassword retrieveOTP (String email);
    Business getBusinessbyEmail (String email);
}