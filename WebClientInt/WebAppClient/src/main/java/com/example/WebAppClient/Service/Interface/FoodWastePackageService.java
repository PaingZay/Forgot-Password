package com.example.WebAppClient.Service.Interface;

import java.util.List;


import com.example.WebAppClient.Model.FoodWastePackage;

public interface FoodWastePackageService {

    List<FoodWastePackage> getPackageList ();
    List<FoodWastePackage> getPendingList (Long id);
    List<FoodWastePackage> getHistoryList (Long id);
    FoodWastePackage getPackagebyId (Long id);
    FoodWastePackage createPackage (FoodWastePackage foodWastePackage);
    FoodWastePackage updateCollected (Long id);
    FoodWastePackage updateCancelled (Long id);
    Boolean removeHistoryById(Long id);
    Boolean removeAllHistoryByBizId(Long id);
}