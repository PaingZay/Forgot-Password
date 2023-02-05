package com.example.WebAppClient.Model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	private int id;

    private String name;

    private String category;

    private int period;

    private String description;


    public Item (String name, String category, Integer period, String description){
        this.name = name;
        this.category = category;
        this.period = period;
        this.description = description;
    }
}
