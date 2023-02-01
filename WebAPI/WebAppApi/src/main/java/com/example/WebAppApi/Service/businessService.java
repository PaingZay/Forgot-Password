package com.example.WebAppApi.Service;

import java.util.List;
import java.util.Optional;

import com.example.WebAppApi.Model.Business;



public interface BusinessService {

    Business createUser (Business business);
    List<Business> getUserList ();
    Optional<Business> getUserbyId (int id);
}
