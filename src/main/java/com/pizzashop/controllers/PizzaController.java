package com.pizzashop.controllers;

import com.pizzashop.models.Pizza;
import com.pizzashop.productFilters.PizzaFilter;
import com.pizzashop.repositories.PizzaRepository;
import com.pizzashop.specifications.PizzaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by barte on 08/12/2016.
 */
@RestController
@RequestMapping("/pizza")
public class PizzaController {
    @Autowired
    PizzaRepository pizzaRepository;

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody Pizza pizza) {
        pizzaRepository.save(pizza);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Pizza create(@RequestBody Pizza pizza) {
        return pizzaRepository.save(pizza);
    }


    @RequestMapping(value = "/read", method = RequestMethod.POST)
    public List<Pizza> read(
            @RequestBody PizzaFilter pizzaFilter,
            @RequestParam(value = "pageSize", required = true) Integer pageSize,
            @RequestParam(value = "pageNumber", required = true) Integer pageNumber
    ) {
        Specification<Pizza> pizzaSpecification=new PizzaSpecification<>(pizzaFilter);
        List<Pizza> pizzas=
                pizzaRepository
                        .findAll(pizzaSpecification, new PageRequest((pageNumber == null) ? 0 : pageNumber - 1, pageSize))
                        .getContent();


        return pizzas;
    }
}
