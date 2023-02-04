package com.example.WebAppApi.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "FoodWastePackages")
public class FoodWastePackage {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    
    private String packageName;
    private int quantity;
    private LocalTime start;
    private LocalTime end;
    private LocalDate pickUpDate;
    private String description;
    private String category;
    private String[] itemList;

    @ManyToOne
    private Business business;

    @OneToMany (mappedBy = "foodwastepackage")
    List <Item> items;

    public FoodWastePackage (String packageName, int quantity, LocalTime start, LocalTime end, LocalDate pickUpDate, String description, String category)
    {
        this.packageName = packageName;
        this.quantity = quantity;
        this.start = start;
        this.end = end;
        this.pickUpDate = pickUpDate;
        this.description = description;
        this.category = category;
    }

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
