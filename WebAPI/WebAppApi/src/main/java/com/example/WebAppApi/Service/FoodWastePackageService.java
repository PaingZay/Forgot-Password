package com.example.WebAppApi.Service;

import java.util.List;

import com.example.WebAppApi.Model.FoodWastePackage;



public interface FoodWastePackageService {

    FoodWastePackage createPackage (FoodWastePackage foodwastepackage);
    List<FoodWastePackage> getPackageList ();
    List<FoodWastePackage> getPendingList (Long id);
    List<FoodWastePackage> getHistoryList (Long id);
    FoodWastePackage updatePackage(Long id);
    FoodWastePackage updateCancelled(Long id);
    FoodWastePackage getPackageById(Long id);
    Boolean removeHistoryById(Long memberId);
    Boolean removeAllHistoryById(Long id);
}