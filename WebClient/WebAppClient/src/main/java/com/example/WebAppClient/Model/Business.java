package com.example.WebAppClient.Model;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table (name = "Users")
public class Business 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String emailAddress;

    private String password;

    private String category;

    private String businessName;

    private String branch;

    private String businessType;

    private String address;

    private String postalCode;

    private String contactNumber;

    private String[] openingDays;

    private LocalTime openingTime;

    private LocalTime closingTime;

    @OneToMany (mappedBy = "business")
    List <Collection> collections;


    public Business (String emailAddress, String password, String category, String businessName, String branch, String businessType, String address, 
                String postalCode, String contactNumber, String[] openingDays, LocalTime openingTime, LocalTime closingTime) 
    {

        this.emailAddress = emailAddress;
        this.password = password;
        this.category = category;
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

