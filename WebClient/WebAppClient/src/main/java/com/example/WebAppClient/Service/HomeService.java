package com.example.WebAppClient.Service;

import com.example.WebAppClient.DTO.FormData;
import com.example.WebAppClient.Model.Business;

public interface HomeService {

    Business authenticate (FormData formData);

}