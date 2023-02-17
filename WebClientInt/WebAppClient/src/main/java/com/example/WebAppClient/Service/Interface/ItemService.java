package com.example.WebAppClient.Service.Interface;

import java.util.List;


import com.example.WebAppClient.Model.Item;

public interface ItemService {

    List<Item> getItemListByBizId(Long id);
    // List<Item> getItemListByBizId();
    Item createItem (Item item);
    Item getItemByName (String name);
    Item getItemById (Long id);
	Boolean deleteItem(Long id);
    Item updateItem (Item item);
}