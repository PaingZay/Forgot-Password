package com.example.WebAppApi.Service;

import java.util.List;

import com.example.WebAppApi.Model.Item;



public interface ItemService {

    Item createItem (Item item);
    List<Item> getItemList ();
}