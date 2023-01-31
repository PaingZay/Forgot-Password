package com.example.WebAppClient.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table (name = "Collections")
public class Collection {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    
    private String packageName;

    private int quantity;

    private LocalTime start;

    private LocalTime end;

    private LocalDate pickUpDate;

    private String description;

    @ManyToOne
    private Business business;

    @OneToMany (mappedBy = "collection")
    List<Item> items;

    @OneToOne (mappedBy = "Collection")
    List<Receiver> receiver;

}
