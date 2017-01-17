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
 * Created by barte on 09/12/2016.
 */
@RestController
@RequestMapping("/sauce")
public class SauceController {
    @Autowired
    SauceRepository sauceRepository;

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody @Valid Sauce sauce){
        sauceRepository.save(sauce);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Sauce create(@RequestBody @Valid Sauce sauce){
        return sauceRepository.save(sauce);
    }

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
}
