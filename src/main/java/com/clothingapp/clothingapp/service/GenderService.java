package com.clothingapp.clothingapp.service;

import com.clothingapp.clothingapp.controller.GenderController;
import com.clothingapp.clothingapp.exception.InformationExistException;
import com.clothingapp.clothingapp.exception.InformationNotFoundException;
import com.clothingapp.clothingapp.model.Gender;
import com.clothingapp.clothingapp.model.Item;
import com.clothingapp.clothingapp.repository.GenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class GenderService {

    private static final Logger LOGGER = Logger.getLogger(GenderService.class.getName());


    public GenderRepository genderRepository;

    @Autowired
    public void setGenderRepository(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }


    public List<Item> getGenderItems(Long genderId) {
        LOGGER.info("calling getGenderItems from GenderService");
        Optional<Gender> gender = genderRepository.findById(genderId);
        if (gender.isPresent()) {
            return gender.get().getItemList();
        } else {
            throw new InformationNotFoundException("gender with id of "+ genderId + " was not found");
        }
    }

    public Gender createGender(Gender genderObject) {
        LOGGER.info("calling createGender from the GenderService");
        Optional<Gender> gender = genderRepository.findByName(genderObject.getName());
        if (gender.isPresent()) {
            throw new InformationExistException("gender with name of " + genderObject.getName() + " already exists");
        } else {
            return genderRepository.save(genderObject);
        }
    }
}
