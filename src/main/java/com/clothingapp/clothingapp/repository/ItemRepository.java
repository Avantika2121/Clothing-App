package com.clothingapp.clothingapp.repository;

import com.clothingapp.clothingapp.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {


    Optional<Item> findByName (String name);
}
