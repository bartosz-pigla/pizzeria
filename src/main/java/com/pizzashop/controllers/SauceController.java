package com.pizzashop.controllers;

import com.pizzashop.models.Sauce;
import com.pizzashop.productFilters.SauceFilter;
import com.pizzashop.repositories.SauceRepository;
import com.pizzashop.specifications.SauceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Kontroler zawierający REST servicy wykonujace operacje CRUD na encji Sos
 * Created by Bartosz Pigla on 09/12/2016.
 */
@RestController
@RequestMapping("/sauce")
@CrossOrigin(origins = "http://localhost:63342")
public class SauceController {
    @Autowired
    SauceRepository sauceRepository;

    /**
     * aktualizuje sos o id istniejącym w bazie danych
     * @param sauce sos do zaktualizowania
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody @Valid Sauce sauce){
        sauceRepository.save(sauce);
    }

    /**
     * zapisuje sos i nadaje mu id
     * @param sauce sos który ma zostać zapisany w bazie danych
     * @return  utworzony sos
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Sauce create(@RequestBody @Valid Sauce sauce){
        Sauce sauce1 = sauceRepository.save(sauce);
        return sauce1;
    }

    /**
     * zwraca sosy spełniające kryteria wyspecyfikowane w parametrze sauceFilter
     * @param sauceFilter kryteria które musi spełnić sos
     * @param pageSize maksymalna liczba sosów do pobrania z bazy danych
     * @param pageNumber numer strony
     * @return lista sosów
     */
    @RequestMapping(value = "/read", method = RequestMethod.POST)
    public List<Sauce> read(
            @RequestBody SauceFilter sauceFilter,
            @RequestParam(value = "pageSize", required = true) Integer pageSize,
            @RequestParam(value = "pageNumber", required = true) Integer pageNumber
    ) {
        Specification<Sauce> sauceSpecification=new SauceSpecification<>(sauceFilter);
        List<Sauce> sauces=
                sauceRepository
                        .findAll(sauceSpecification, new PageRequest((pageNumber == null) ? 0 : pageNumber - 1, pageSize))
                        .getContent();


        return sauces;
    }

    /**
     * liczba wszystkich sosów istniejących w bazie danych spełniających kryteria wyspecyfikowane w parametrze sauceFilter
     * @param sauceFilter kryteria które musi spełnić sos
     * @return liczba sosów
     */
    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Long count(
            @RequestBody SauceFilter sauceFilter
    ) {
        Specification<Sauce> sauceSpecification=new SauceSpecification<>(sauceFilter);
        return
                sauceRepository
                        .count(sauceSpecification);
    }
}
