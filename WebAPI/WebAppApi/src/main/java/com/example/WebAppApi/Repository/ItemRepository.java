package com.example.WebAppApi.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.WebAppApi.Model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("SELECT i FROM Item i where i.name = :name")
    Item findByName (@Param("name") String name);

    @Query("SELECT i FROM Item i where i.id = :id")
    Item findItemById (@Param("id") Long id);

    @Query("SELECT i FROM Item i JOIN i.business biz WHERE biz.id=:bizId")
    List<Item> findByBuzId (@Param("bizId") Long id);
}
