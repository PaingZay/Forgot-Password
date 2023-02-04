package com.example.WebAppApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebAppApi.Model.FoodWastePackage;

public interface FoodWastePackageRepository extends JpaRepository<FoodWastePackage, Integer> {
    
}
