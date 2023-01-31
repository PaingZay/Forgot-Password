package com.example.WebAppClient.Model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String businessName;

    private String branch;

    private String businessType;

    private String address;

    private String postalCode;

    private String contactNumber;

    private String[] openingDays;

    private LocalTime openingTime;

    private LocalTime closingTime;

    @OneToOne
    private User user;

    public Business(String businessName, String branch, String businessType, String address, String postalCode, String contactNumber, String[] openingDays, LocalTime openingTime, LocalTime closingTime)
    {
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


