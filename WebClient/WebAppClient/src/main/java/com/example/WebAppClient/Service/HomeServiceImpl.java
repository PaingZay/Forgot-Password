package com.example.WebAppClient.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class HomeServiceImpl implements HomeService{
    
    @Autowired
    WebClient webClient;
    @Override
    public boolean authenticate(String email, String password) {
        //web client call to API end point then do the comparison
        if (email.equals("breadtalk.amk@gmail.com") && password.equals("asdf")) {
        return true;
    }
    else return false;
    }
}
