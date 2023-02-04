package com.example.WebAppApi.Service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.WebAppApi.Model.Item;
import com.example.WebAppApi.Repository.ItemRepository;


@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    private ItemRepository itemRepository;

    @Override
    public Item createItem(Item item) {
        return itemRepository.saveAndFlush(item);
    }  

    @Override
    public List<Item> getItemList() {
        return itemRepository.findAll();
    }
}
