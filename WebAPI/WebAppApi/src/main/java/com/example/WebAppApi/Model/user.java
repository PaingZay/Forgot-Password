package com.example.WebAppApi.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table (name = "Users")
public class User 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String emailAddress;

    private String password;

    private String category;

    public User (){

    }

    public User (String emailAddress, String password, String category){
        this.emailAddress = emailAddress;
        this.password = password;
        this.category = category;
    }

}
