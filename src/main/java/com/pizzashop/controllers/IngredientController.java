package com.pizzashop.controllers;

import com.pizzashop.models.Ingredient;
import com.pizzashop.repositories.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping("/read/all")
    public ResponseEntity<List<Ingredient>> readAll(){
        List<Ingredient> ingredients=null;
        try{
            ingredients = ingredientRepository.findAll();
            return ResponseEntity.ok(ingredients);
        }
        catch (Exception exception){
            return new ResponseEntity<>(ingredients, HttpStatus.NO_CONTENT);
        }
    }
}
