package com.example.WebAppApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WebAppApi.Model.User;

public interface UserRepository extends JpaRepository <User, Integer> {

    
    
}
