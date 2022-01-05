package com.clothingapp.clothingapp.service;

import com.clothingapp.clothingapp.exception.InformationExistException;
import com.clothingapp.clothingapp.exception.InformationNotFoundException;
import com.clothingapp.clothingapp.model.Category;
import com.clothingapp.clothingapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CategoryService {

    private static final Logger LOGGER = Logger.getLogger(ItemService.class.getName());

    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    //.../categories == Create a new category
    public Category createCategory(Category categoryObject) {
        LOGGER.info("calling createCategory from CategoryService");
        Optional<Category> category = categoryRepository.findByName(categoryObject.getName());
        if (category.isPresent()) {
            throw new InformationExistException("category with name " +categoryObject.getName() +" already exists");
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
            throw new InformationNotFoundException("category with Id of "+ categoryId + " was not found");
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
            throw new InformationNotFoundException("category with id of "+ categoryId+ " was not found");
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
            throw new InformationNotFoundException("category with id of "+ categoryId + "does not exists");
        }
    }
}
