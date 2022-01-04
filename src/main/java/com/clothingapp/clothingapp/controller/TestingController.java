package com.clothingapp.clothingapp.controller;

import com.clothingapp.clothingapp.model.Item;
import com.clothingapp.clothingapp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
public class TestingController {

    private static final Logger LOGGER = Logger.getLogger(TestingController.class.getName());

    private ItemService itemService;

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }
    @PostMapping("/items")
    public Item createItem(@RequestBody Item itemObject) {
        LOGGER.info("calling create item from TestingController");
        return itemService.createItem(itemObject);
    }
    @DeleteMapping("/items/{itemId}")
    public String updateItem(@PathVariable Long itemId) {
        LOGGER.info("calling getItem from TestingController");
        return itemService.deleteItem(itemId);
    }
}
