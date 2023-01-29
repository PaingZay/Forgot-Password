package com.example.WebAppApi.Model;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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

