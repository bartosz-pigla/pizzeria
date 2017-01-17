package com.pizzashop.controllers;

import com.pizzashop.models.Ingredient;
import com.pizzashop.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by barte on 13/12/2016.
 */
@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    @Autowired
    IngredientRepository ingredientRepository;

    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping("/read/all")
    public List<Ingredient> readAll(){
        return ingredientRepository.findAll();
    }
}
