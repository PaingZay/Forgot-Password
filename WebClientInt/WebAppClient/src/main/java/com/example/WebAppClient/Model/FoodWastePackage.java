package com.example.WebAppClient.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodWastePackage implements Serializable
{

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String packageName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime startTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime endTime;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    private String status = "Pending";
    private String description;
    private String itemList;

    @ManyToOne
    private Business business;

    public FoodWastePackage (String packageName, LocalTime start, LocalTime end, LocalDate pickUpDate, String description, Business business)
    {
        this.packageName = packageName;
        this.startTime = start;
        this.endTime = end;
        this.pickUpDate = pickUpDate;
        this.description = description;
        this.business = business;
    }

}
