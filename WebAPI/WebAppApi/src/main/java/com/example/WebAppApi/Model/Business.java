package com.example.WebAppApi.Model;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Business implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String email;
    private String password;
    private String businessName;
    private String businessType;
    private String branch;
    private String address;
    private String postalCode;
    private String contactNumber;
    private String openingDays;
    private LocalTime openingTime;
    private LocalTime closingTime;

    public Business (String email, String password, String businessName, String branch, String businessType, String address, 
                     String postalCode, String contactNumber, String openingDays, LocalTime openingTime, LocalTime closingTime) 
    {

        this.email = email;
        this.password = password;
        this.businessName = businessName;
        this.branch = branch;
        this.businessType = businessType;
        this.address = address;
        this.postalCode = postalCode;
        this.contactNumber = contactNumber;
        this.openingDays = openingDays;
        this.openingTime = openingTime;
        this.closingTime = closingTime;

    }
}

