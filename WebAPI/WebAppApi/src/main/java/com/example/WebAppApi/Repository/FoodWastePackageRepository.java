package com.example.WebAppApi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.WebAppApi.Model.FoodWastePackage;

public interface FoodWastePackageRepository extends JpaRepository<FoodWastePackage, Long> {

    @Query("SELECT p FROM FoodWastePackage p where p.status = 'Cancelled' and p.business.id = :id or p.status = 'Collected' and p.business.id = :id")
    List<FoodWastePackage> findByCancelledStatus (@Param("id") Long id);

    @Query("SELECT p FROM FoodWastePackage p where p.status = 'Pending' and p.business.id = :id")
    List<FoodWastePackage> findByPendingStatus (@Param("id") Long id);

    @Query("SELECT p FROM FoodWastePackage p where p.id = :id")
    FoodWastePackage findPackageById (@Param("id") Long id);
    
    @Query("SELECT p FROM FoodWastePackage p JOIN p.business biz WHERE biz.id = :id AND (p.status = 'Collected'  OR p.status = 'Cancelled')")
    List<FoodWastePackage> findAllFoodWastePackagesHistoryByBusinessUserId(@Param("id") Long id);
    
}
