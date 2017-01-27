package com.pizzashop.controllers;

import com.pizzashop.models.Drink;
import com.pizzashop.models.Pizza;
import com.pizzashop.productFilters.DrinkFilter;
import com.pizzashop.productFilters.PizzaFilter;
import com.pizzashop.repositories.DrinkRepository;
import com.pizzashop.specifications.DrinkSpecification;
import com.pizzashop.specifications.PizzaSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * operacje CRUD na encji Drink
 * Created by Bartosz Pigla on 09/12/2016.
 */
@RestController
@RequestMapping("/drink")
@CrossOrigin(origins = "http://localhost:63342")
public class DrinkController {
    /**
     * repozytorium napojów z którego korzystają webservicy
     */
    @Autowired
    DrinkRepository drinkRepository;

    /**
     * metoda aktualizująca napój do bazy danych
     * @param drink obiekt JSON otrzymany od klienta
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody @Valid Drink drink){
        drinkRepository.save(drink);
    }

    /**
     *metoda zapisująca napój do bazy danych
     * @param drink
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Drink create(@RequestBody @Valid Drink drink){
        drinkRepository.save(drink);
        return drink;
    }

    /**
     * metoda odczytująca listę napojów
     * @param drinkFilter filtr produktów
     * @param pageSize liczba napojów do pobrania
     * @param pageNumber numer strony
     * @return lista napojów
     */
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

    /**
     * pobiera listę wszystkich napojów spełniających kryteria
     * @param drinkFilter kryteria które musi spełnić napój
     * @return lista napojów spełniających dane kryteria
     */
    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Long count(
            @RequestBody DrinkFilter drinkFilter
    ) {
        Specification<Drink> drinkSpecification=new DrinkSpecification<>(drinkFilter);
        return
                drinkRepository
                        .count(drinkSpecification);
    }
}