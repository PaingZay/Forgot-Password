package com.example.WebAppClient.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class OpeningDays {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    private String name;

    public OpeningDays (String name){
        this.name = name;
    }
}
