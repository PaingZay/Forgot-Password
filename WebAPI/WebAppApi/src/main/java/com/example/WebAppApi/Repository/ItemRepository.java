package com.example.WebAppApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebAppApi.Model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    
}
