package com.example.WebAppClient.Service.Interface;

import com.example.WebAppClient.DTO.FormData;
import com.example.WebAppClient.Model.Business;
import com.example.WebAppClient.Model.OneTimePassword;

public interface HomeService {

    Business authenticate (FormData formData);
    OneTimePassword generateOTP (String email);
    OneTimePassword retrieveOTP (String email);
}