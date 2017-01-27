package com.pizzashop.controllers;

import com.pizzashop.models.Pizza;
import com.pizzashop.productFilters.PizzaFilter;
import com.pizzashop.repositories.PizzaRepository;
import com.pizzashop.specifications.PizzaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * operacje CRUD na encji Pizza
 * Created by Bartosz Pigla on 08/12/2016.
 */
@RestController
@RequestMapping("/pizza")
//@CrossOrigin(origins = "http://localhost:63342")
public class PizzaController {

    @Autowired
    PizzaRepository pizzaRepository;

    /**
     * aktualizuje istniejącą w bazie danych pizzę o podanym Id
     * @param pizza zmieniona pizza do zakutalizowania w bazie danych o id pizzy istniejącej już w bazie danych
     */
    @CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody @Valid Pizza pizza) {
        pizzaRepository.save(pizza);
    }

    /**
     * tworzy nową pizzę
     * @param pizza pizza która będzie dodana do menu
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Pizza create(@RequestBody @Valid Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    /**
     * zwraca listę pizz spełniających wszystkie kryteria zawarte w filtrze pizzaFilter
     * @param pizzaFilter kryteria które musi spełnić pizza
     * @param pageSize liczba pizz do pobrania z bazy danych
     * @param pageNumber numer strony
     * @return lista pizz
     */
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

    /**
     * liczba wszystkich pizz w bazie danych spełniających wszystkie kryteria podane w parametrze pizzaFilter
     * @param pizzaFilter kryteria które musi spełnić pizza
     * @return liczba pizz
     */
    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Long count(
            @RequestBody PizzaFilter pizzaFilter
    ) {
        Specification<Pizza> pizzaSpecification=new PizzaSpecification<>(pizzaFilter);
        return
                pizzaRepository
                        .count(pizzaSpecification);
    }

//
//    @RequestMapping(value = "/read", method = RequestMethod.POST)
//    public List<Pizza> read(
//            @RequestBody PizzaFilter pizzaFilter,
//            @RequestParam(value = "pageSize", required = true) Integer pageSize,
//            @RequestParam(value = "pageNumber", required = true) Integer pageNumber
//    ) {
//        Specification<Pizza> pizzaSpecification=new PizzaSpecification<>(pizzaFilter);
//        List<Pizza> pizzas=
//                pizzaRepository
//                        .findAll(pizzaSpecification, new PageRequest((pageNumber == null) ? 0 : pageNumber - 1, pageSize))
//                        .getContent();
//
//        return pizzas;
//    }
}
