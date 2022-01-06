package com.clothingapp.clothingapp.service;

import com.clothingapp.clothingapp.exception.InformationExistException;
import com.clothingapp.clothingapp.exception.InformationNotFoundException;
import com.clothingapp.clothingapp.model.Category;
import com.clothingapp.clothingapp.model.Item;
import com.clothingapp.clothingapp.repository.CategoryRepository;
import com.clothingapp.clothingapp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CategoryService {

    private static final Logger LOGGER = Logger.getLogger(ItemService.class.getName());

    private CategoryRepository categoryRepository;
    private ItemRepository itemRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    //.../categories == Create a new category
    public Category createCategory(Category categoryObject) {
        LOGGER.info("calling createCategory from CategoryService");
        Optional<Category> category = categoryRepository.findByName(categoryObject.getName());
        if (category.isPresent()) {
            throw new InformationExistException("category with name " + categoryObject.getName() + " already exists");
        } else {
            return categoryRepository.save(categoryObject);
        }
    }

    //.../categories == Get all categories
    public List<Category> getCategories() {
        LOGGER.info("calling getCategories from CategoryService");
        return categoryRepository.findAll();
    }

    //.../category/{categoryId} == Get a category by Id
    public Category getCategory(Long categoryId) {
        LOGGER.info("calling getCategory from CategoryService");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            return category.get();
        } else {
            throw new InformationNotFoundException("category with Id of " + categoryId + " was not found");
        }
    }

    //.../category/{categoryId} == Delete a category by Id
    public String deleteCategory(Long categoryId) {
        LOGGER.info("calling deleteCAtegory from CategoryService");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            categoryRepository.deleteById(categoryId);
            return "Category with id of " + categoryId + " has been deleted";
        } else {
            throw new InformationNotFoundException("category with id of " + categoryId + " was not found");
        }
    }

    //.../category/{categoryId} == Update an existing category
    public Category updateCategory(Long categoryId, Category categoryObject) {
        LOGGER.info("calling updateCategory from CategoryService");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            category.get().setName(categoryObject.getName());
            category.get().setDescription(categoryObject.getDescription());
            return categoryRepository.save(category.get());
        } else {
            throw new InformationNotFoundException("category with id of " + categoryId + "does not exists");
        }
    }

    //.../category/{categoryId}/items  == Get all items within that category
    public List<Item> getCategoryItems(Long categoryId) {
        LOGGER.info("calling getCategoryItems from CategoryService");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            return category.get().getItemList();
        } else {
            throw new InformationNotFoundException("category with id of " + categoryId + " was not found");
        }
    }

    //.../category/{categoryId}/items == Create an item from a category
    public Item createItemFromCategory(Long categoryId, Item itemObject) {
        LOGGER.info("calling createItemFromCategory from CategoryService");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            Optional<Item> item = itemRepository.findByName(itemObject.getName());
            if (item.isPresent()) {
                throw new InformationExistException("item with name of " + itemObject.getName() + "already exists");
            } else {
                itemObject.setCategory(category.get());
                return itemRepository.save(itemObject);
            }
        } else {
            throw new InformationNotFoundException("category with Id of " + categoryId + "does not exists");
        }
    }

    public Item getCategoryItem(Long categoryId, Long itemId) {
        LOGGER.info("calling getCategoryItem from CategoryService");
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category == null) {
            throw new InformationNotFoundException("category with id " + categoryId +
                    " not found");
        }
        Optional<Item> item = itemRepository.findById(
                categoryId).stream().filter(p -> p.getId().equals(itemId)).findFirst();
        if (!item.isPresent()) {
            throw new InformationNotFoundException("item with id " + itemId +
                    " does not exist");
        }
        return item.get();
    }

    public Item updateCategoryItem(Long categoryId, Long itemId, Item itemObject) {

        Optional category = categoryRepository.findById(categoryId);
        if (category == null) {
            throw new InformationNotFoundException("category with id " + categoryId +
                    " does not exist");
        }
        Optional<Item> item = itemRepository.findById(
                categoryId).stream().filter(p -> p.getId().equals(itemId)).findFirst();
        if (!item.isPresent()) {
            throw new InformationNotFoundException("item with id " + itemId +
                    " does not exist");
        }
//        Item oldItem = itemRepository.findByNameAndIdIsNot(
//                itemObject.getName(), itemId);
//        if (oldItem != null) {
//            throw new InformationExistException("item with name " + oldItem.getName() + " already exists");
//        }
        item.get().setName(itemObject.getName());
        item.get().setDescription(itemObject.getDescription());

        return itemRepository.save(item.get());
    }
    public Item deleteCategoryItem(Long categoryId, Long itemId) {

        Optional category = categoryRepository.findById(categoryId);
        if (category == null) {
            throw new InformationNotFoundException("category with id " + categoryId +
                    "does not exist");
        }
        Optional<Item> item = itemRepository.findById(
                categoryId).stream().filter(p -> p.getId().equals(itemId)).findFirst();
        if (!item.isPresent()) {
            throw new InformationNotFoundException("item with id " + itemId +
                    "does not exist");
        }
        itemRepository.deleteById(item.get().getId());
        return null;
    }


}
