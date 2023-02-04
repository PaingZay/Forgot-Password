package com.example.WebAppClient.Service;

import java.util.List;

import com.example.WebAppClient.Model.Item;

public interface ItemService {

    List<Item> getItemList ();
    Item createItem (Item item);
}