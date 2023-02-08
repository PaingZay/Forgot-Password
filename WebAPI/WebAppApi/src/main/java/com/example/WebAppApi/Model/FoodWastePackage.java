package com.example.WebAppApi.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class FoodWastePackage implements Serializable
{

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    
    private String packageName;
    private LocalTime start;
    private LocalTime end;
    private int quantity;
    private String category;
    private LocalDate pickUpDate;
    private String description;
    private String itemList;
    private String status = "Pending";

    @ManyToOne
    private Business business;



    // public FoodWastePackage (String packageName, int quantity, LocalTime start, LocalTime end, LocalDate pickUpDate, String description, String category)
    // {
    //     this.packageName = packageName;
    //     this.quantity = quantity;
    //     this.start = start;
    //     this.end = end;
    //     this.pickUpDate = pickUpDate;
    //     this.description = description;
    //     this.category = category;
    // }

    public FoodWastePackage (String packageName, int quantity, LocalTime start, LocalTime end, LocalDate pickUpDate, String description, String category, Business business)
    {
        this.packageName = packageName;
        this.quantity = quantity;
        this.start = start;
        this.end = end;
        this.pickUpDate = pickUpDate;
        this.description = description;
        this.category = category;
        this.business = business;
    }

}
