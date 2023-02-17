package com.example.WebAppClient.Service.Interface;

import java.util.List;
import com.example.WebAppClient.DTO.FormData;
import com.example.WebAppClient.Model.Business;

public interface BusinessService {

    List<Business> getUserList ();
    Business getUserbyId (Long id);
    Business create (Business business);
    Business updateBusiness (Business business);
    Boolean deleteBusiness (Long id);
    Business getBusinessbyEmail (String email);
    Business updatePasswordByEmail (FormData formdata);
}
