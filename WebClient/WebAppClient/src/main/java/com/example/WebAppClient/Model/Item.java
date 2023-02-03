package com.example.WebAppClient.Model;

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

public class Item {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    private String name;

    private String category;

    private int period;

    private String description;

    // @ManyToOne 
    // private Collection collection;
    
    public Item (String name, String category, Integer period, String description){
        this.name = name;
        this.category = category;
        this.period = period;
        this.description = description;
    }
}
