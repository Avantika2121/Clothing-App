package com.clothingapp.clothingapp.repository;

import com.clothingapp.clothingapp.model.Category;
import com.clothingapp.clothingapp.model.Gender;
import com.clothingapp.clothingapp.model.Item;
import com.clothingapp.clothingapp.model.Seasons;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByName (String name);
    //Optional<List<Item>> findAllByCategoryAndGenderAndSeasons (Category category, Gender gender, Seasons seasons);
    Optional<List<Item>> findAllByCategoryIdAndGenderIdAndSeasonsId (Long categoryId, Long genderId, Long seasonsId);

}
