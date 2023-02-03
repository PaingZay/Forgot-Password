package com.example.WebAppApi.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Items")
public class Item {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    private String category;

    private int period;

    private String description;

    // @ManyToOne 
    // private Collection collection;
    
}
