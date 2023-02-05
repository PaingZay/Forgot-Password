package com.example.WebAppApi.Service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.WebAppApi.Model.FoodWastePackage;
import com.example.WebAppApi.Repository.FoodWastePackageRepository;


@Service
public class FoodWastePackageServiceImpl implements FoodWastePackageService {

    @Resource
    private FoodWastePackageRepository foodwastepackageRepository;

    @Override
    public FoodWastePackage createPackage(FoodWastePackage foodwastepackage) {
        return foodwastepackageRepository.saveAndFlush(foodwastepackage);
    }  

    @Override
    public List<FoodWastePackage> getPackageList() {
        List<FoodWastePackage> foodwastepackages = foodwastepackageRepository.findAll();
        return foodwastepackages;
    }
}
