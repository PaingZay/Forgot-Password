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

    @Override
    public List<FoodWastePackage> getHistoryList() {
        List<FoodWastePackage> foodwastepackages = foodwastepackageRepository.findByCancelledStatus();
        return foodwastepackages;
    }

    @Override
    public List<FoodWastePackage> getPendingList() {
        List<FoodWastePackage> foodwastepackages = foodwastepackageRepository.findByPendingStatus();
        return foodwastepackages;
    }

    @Override
    public FoodWastePackage updatePackage(int id) {
        FoodWastePackage fwp = foodwastepackageRepository.findById(id);
        fwp.setStatus("Collected");
        return foodwastepackageRepository.save(fwp);
    }

    @Override
    public FoodWastePackage updateCancelled(int id) {
        FoodWastePackage fwp = foodwastepackageRepository.findById(id);
        fwp.setStatus("Cancelled");
        return foodwastepackageRepository.save(fwp);
    }

    @Override
    public FoodWastePackage getPackageById(int id) {
        System.out.println("API SERVICE IMPL");
        return foodwastepackageRepository.findById(id);
    }
}
