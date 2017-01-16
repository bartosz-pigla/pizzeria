package com.pizzashop.controllers;

import com.pizzashop.models.Seasoning;
import com.pizzashop.repositories.SeasoningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by barte on 13/12/2016.
 */
@RestController
@RequestMapping("/seasoning")
public class SeasoningController {
    @Autowired
    SeasoningRepository seasoningRepository;

    @RequestMapping("/read/all")
    public List<Seasoning> readAll(){
        return seasoningRepository.findAll();
    }
}
