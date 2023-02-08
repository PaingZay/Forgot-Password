package com.example.WebAppApi.Service;

import java.util.List;

import com.example.WebAppApi.Model.FoodWastePackage;



public interface FoodWastePackageService {

    FoodWastePackage createPackage (FoodWastePackage foodwastepackage);
    List<FoodWastePackage> getPackageList ();
    List<FoodWastePackage> getPendingList ();
    List<FoodWastePackage> getHistoryList ();
    FoodWastePackage updatePackage(int id);
    FoodWastePackage updateCancelled(int id);
    FoodWastePackage getPackageById(int id);
}