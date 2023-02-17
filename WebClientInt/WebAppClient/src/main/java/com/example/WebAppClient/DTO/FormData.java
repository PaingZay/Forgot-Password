package com.example.WebAppClient.DTO;
import lombok.Data;

@Data
public class FormData {

    private String email;
    private String password;

    public FormData(String email, String password)
    {
        this.email = email;      
        this.password = password;
    }
}
