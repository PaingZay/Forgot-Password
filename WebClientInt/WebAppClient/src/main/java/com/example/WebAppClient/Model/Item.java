package com.example.WebAppClient.Model;

import java.io.Serializable;

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
public class Item implements Serializable
{

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private String name;

    private String category;

    private int period;

    private String manufacturer;

    private Float weight;

    @ManyToOne
    private Business business;

    public Item (String name, String category, Integer period, String manufacturer, Float weight){
        this.name = name;
        this.category = category;
        this.period = period;
        this.manufacturer = manufacturer;
        this.weight = weight;
    }
}
