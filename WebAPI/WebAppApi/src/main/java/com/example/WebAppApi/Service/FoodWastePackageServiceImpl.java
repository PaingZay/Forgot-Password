package com.example.WebAppApi.Service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

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
    public List<FoodWastePackage> getHistoryList(Long id) {
        List<FoodWastePackage> foodwastepackages = foodwastepackageRepository.findByCancelledStatus(id);
        return foodwastepackages;
    }

    @Override
    public List<FoodWastePackage> getPendingList(Long id) {
        List<FoodWastePackage> foodwastepackages = foodwastepackageRepository.findByPendingStatus(id);
        return foodwastepackages;
    }

    @Override
    public FoodWastePackage updatePackage(Long id) {
        FoodWastePackage fwp = foodwastepackageRepository.findPackageById(id);
        fwp.setStatus("Collected");
        return foodwastepackageRepository.save(fwp);
    }

    @Override
    public FoodWastePackage updateCancelled(Long id) {
        FoodWastePackage fwp = foodwastepackageRepository.findPackageById(id);
        fwp.setStatus("Cancelled");
        return foodwastepackageRepository.save(fwp);
    }

    @Override
    public FoodWastePackage getPackageById(Long id) {
        return foodwastepackageRepository.findPackageById(id);
    }

    @Override
    public Boolean removeHistoryById(Long memberId) {
        try {
            foodwastepackageRepository.deleteById(memberId);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public Boolean removeAllHistoryById(Long id) {
        try {
            List<FoodWastePackage> foodWastePackagesHistoryList = foodwastepackageRepository.findAllFoodWastePackagesHistoryByBusinessUserId(id);
            if (!foodWastePackagesHistoryList.isEmpty()) { // make sure list not empty
                for(FoodWastePackage foodWastePackage : foodWastePackagesHistoryList) {
                	foodwastepackageRepository.delete(foodWastePackage);  // one by one delete, all items must be deleted else roll back if something happen in between
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
