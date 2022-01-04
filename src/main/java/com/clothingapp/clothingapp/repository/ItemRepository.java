package com.clothingapp.clothingapp.repository;

import com.clothingapp.clothingapp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findByName (String name);
}
