package com.example.WebAppClient.Service;

import java.util.List;

import com.example.WebAppClient.DTO.RequestCollectionForm;
import com.example.WebAppClient.Model.FoodWastePackage;

public interface FoodWastePackageService {

    List<FoodWastePackage> getPackageList ();
    FoodWastePackage createPackage (RequestCollectionForm requestcollectionform);
}