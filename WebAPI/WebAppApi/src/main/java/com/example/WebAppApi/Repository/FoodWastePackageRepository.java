package com.example.WebAppApi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.WebAppApi.Model.FoodWastePackage;

public interface FoodWastePackageRepository extends JpaRepository<FoodWastePackage, Integer> {
    @Query("SELECT p FROM FoodWastePackage p where p.id = :id")
    FoodWastePackage findById (@Param("id") int id);

    @Query("SELECT p FROM FoodWastePackage p where p.status = 'Cancelled' or p.status = 'Collected' ")
    List<FoodWastePackage> findByCancelledStatus ();

    @Query("SELECT p FROM FoodWastePackage p where p.status = 'Pending' ")
    List<FoodWastePackage> findByPendingStatus ();
    
}
