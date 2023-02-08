package com.example.WebAppApi.Model;

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
public class FoodWaste implements Serializable
{

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    private int quantity;
    private float weight;

    @ManyToOne
    private FoodWastePackage foodWastePackage;

    @ManyToOne
    private Food foodTemplate;
}
