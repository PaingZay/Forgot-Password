package com.example.WebAppClient.Service;

import java.util.List;

import com.example.WebAppClient.DTO.RequestCollectionForm;
import com.example.WebAppClient.Model.FoodWastePackage;

public interface FoodWastePackageService {

    List<FoodWastePackage> getPackageList ();
    List<FoodWastePackage> getPendingList ();
    List<FoodWastePackage> getHistoryList ();
    FoodWastePackage getPackagebyId (int id);
    FoodWastePackage createPackage (RequestCollectionForm requestcollectionform);
    FoodWastePackage updatePackage (int id);
    FoodWastePackage updateCancelled (int id);
}