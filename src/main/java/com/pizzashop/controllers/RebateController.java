package com.pizzashop.controllers;

import com.pizzashop.models.Rebate;
import com.pizzashop.repositories.RebateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * operacje CRUD na encji Składniki
 * Created by Bartosz Pigla on 14/12/2016.
 */
@RestController
@RequestMapping("/rebate")
public class RebateController {
    @Autowired
    RebateRepository rebateRepository;

    /**
     * zwraca wszystkie rabaty które istnieją w bazie danych
     * @return rabaty
     */
    //@CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping("/read/all")
    public List<Rebate> readAll(){
        return rebateRepository.findAll();
    }
}
