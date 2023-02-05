package com.example.WebAppApi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.WebAppApi.Model.Business;

public interface BusinessRepository extends JpaRepository<Business, Long> {

    @Query("SELECT b FROM Business b where b.id = :id")
    Business getById (@Param("id") Long id);

    @Query("SELECT b FROM Business b where b.email = :email AND b.password=:password")
    Business getByEmail (@Param("email") String email, @Param("password") String password);
    
}
