package com.clothingapp.clothingapp.service;

import com.clothingapp.clothingapp.exception.InformationExistsException;
import com.clothingapp.clothingapp.model.Item;
import com.clothingapp.clothingapp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class ItemService {

    private static final Logger LOGGER = Logger.getLogger(ItemService.class.getName());

    private ItemRepository itemRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    //.../items   == Get all items regardless of Cat, Gender, or Season
    public List<Item> getItemList() {
        LOGGER.info("calling getItemList from ItemService");
        return itemRepository.findAll();
    }
    //.../items   == Create an item (You will have to specify Cat, Gender and Season
    public Item createItem(Item itemObject) {
        LOGGER.info("calling createItem from ItemService");
        if (itemRepository.findByName(itemObject.getName()).equals(itemObject.getName())) {
            throw new InformationExistsException("item with "+ itemObject.getName() + " already exists");
        } else {
            return itemRepository.save(itemObject);
        }
    }

}
