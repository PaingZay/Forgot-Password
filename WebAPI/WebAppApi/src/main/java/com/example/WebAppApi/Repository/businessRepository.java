package com.example.WebAppApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebAppApi.Model.Business;

public interface BusinessRepository extends JpaRepository<Business, Long> {
    
}
