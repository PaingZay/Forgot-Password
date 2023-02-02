package com.example.WebAppClient.Service;

import java.util.List;

import com.example.WebAppClient.Model.Business;

public interface BusinessService {

    List<Business> getUserList ();
    Business getUserbyId (int id);
    Business create (Business business);
}
