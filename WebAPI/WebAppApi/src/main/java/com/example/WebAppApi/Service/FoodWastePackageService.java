package com.example.WebAppApi.Service;

import java.util.List;

import com.example.WebAppApi.Model.FoodWastePackage;



public interface FoodWastePackageService {

    FoodWastePackage createPackage (FoodWastePackage foodwastepackage);
    List<FoodWastePackage> getPackageList ();
}