package com.example.WebAppApi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.WebAppApi.Model.OneTimePassword;

@Repository
public interface OneTimePasswordRepository extends CrudRepository<OneTimePassword, Long> {
    @Query("SELECT o FROM OneTimePassword o where o.email = :email ORDER BY o.id DESC")
    List<OneTimePassword> getOTPByEmail(@Param("email") String email);
}