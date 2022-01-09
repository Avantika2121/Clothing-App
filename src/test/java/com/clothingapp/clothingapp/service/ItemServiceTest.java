package com.clothingapp.clothingapp.service;

import com.clothingapp.clothingapp.ClothingAppApplication;
import com.clothingapp.clothingapp.model.Item;
import com.clothingapp.clothingapp.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClothingAppApplication.class)
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    Item item = new Item(
                "TestingService22",
            "Courtesy of JUnit"
    );

    @Test
    void getItem() {
        String expected = "pants";
        String actual = itemService.getItem(12L).getName();

        assertEquals(expected, actual);
    }

    @Test
    void getItemList() {
        List<Item> actual = itemService.getItemList();

        assertThat(actual, hasSize(18)); //Expected size of the list
    }

    @Test
    void createItem() {
        itemService.createItem(item);
        Optional<Item> expected = itemRepository.findByName(item.getName());

        assertTrue(expected.isPresent());
    }

    @Test
    void updateItem() {
        itemService.updateItem(18L,
                new Item("Updated name", "Updated Desc"));

        Optional<Item> actual = itemRepository.findByName("Updated name");

        assertTrue(actual.isPresent());
    }


    @Test
    void deleteItem() {
        itemService.deleteItem(23L);
        Optional<Item> actual = itemRepository.findById(23L);

        assertFalse(actual.isPresent());
    }

}