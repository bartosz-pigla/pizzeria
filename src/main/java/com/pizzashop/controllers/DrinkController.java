package com.pizzashop.controllers;

import com.pizzashop.models.Drink;
import com.pizzashop.productFilters.DrinkFilter;
import com.pizzashop.repositories.DrinkRepository;
import com.pizzashop.specifications.DrinkSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by barte on 09/12/2016.
 */
@RestController
@RequestMapping("/drink")
public class DrinkController {
    @Autowired
    DrinkRepository drinkRepository;

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody @Valid Drink drink){
        drinkRepository.save(drink);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Drink create(@RequestBody @Valid Drink drink){
        drinkRepository.save(drink);
        return drink;
    }

    @RequestMapping(value = "/read", method = RequestMethod.POST)
    public List<Drink> read(
            @RequestBody DrinkFilter drinkFilter,
            @RequestParam(value = "pageSize", required = true) Integer pageSize,
            @RequestParam(value = "pageNumber", required = true) Integer pageNumber
    ) {
        Specification<Drink> drinkSpecification=new DrinkSpecification<>(drinkFilter);
        List<Drink> drinks=
                drinkRepository
                        .findAll(drinkSpecification, new PageRequest((pageNumber == null) ? 0 : pageNumber - 1, pageSize))
                        .getContent();


        return drinks;
    }
}