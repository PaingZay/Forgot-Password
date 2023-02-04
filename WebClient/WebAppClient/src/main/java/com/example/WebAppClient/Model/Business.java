package com.example.WebAppClient.Model;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Business 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String email;
    private String password;
    private String businessName;
    private String branch;
    private String businessType;
    private String address;
    private String postalCode;
    private String contactNumber;
    private String openingDays;
    
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime openingTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime closingTime;

    @OneToMany (mappedBy = "business")
    List <FoodWastePackage> collections;
    
    public Business (String email, String password, String businessName, String branch, String businessType, String address, 
                    String postalCode, String contactNumber, String openingDays, LocalTime openingTime, LocalTime closingTime) 
    {

        this.email = email;
        this.password = password;
        this.businessName = businessName;
        this.branch = branch;
        this.branch = businessType;
        this.address = address;
        this.postalCode = postalCode;
        this.contactNumber = contactNumber;
        this.openingDays = openingDays;
        this.openingTime = openingTime;
        this.closingTime = closingTime;

    }

    
}

