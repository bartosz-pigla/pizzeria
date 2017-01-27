package com.pizzashop.controllers;

import com.pizzashop.models.Seasoning;
import com.pizzashop.repositories.SeasoningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * operacje CRUD na encji Przyprawy
 * Created by Bartosz Pigla on 13/12/2016.
 */
@RestController
@RequestMapping("/seasoning")
public class SeasoningController {
    @Autowired
    SeasoningRepository seasoningRepository;

    /**
     * Zwraca listę wszystkich sosów istniejących w bazie danych
     * @return lista sosów
     */
    //@CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping("/read/all")
    public List<Seasoning> readAll(){
        return seasoningRepository.findAll();
    }
}
