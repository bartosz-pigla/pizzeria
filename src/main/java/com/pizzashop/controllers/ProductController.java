package com.pizzashop.controllers;

import com.pizzashop.models.Pizza;
import com.pizzashop.models.Product;
import com.pizzashop.productFilters.PizzaFilter;
import com.pizzashop.productFilters.ProductFilter;
import com.pizzashop.repositories.ProductBaseRepository;
import com.pizzashop.repositories.ProductRepository;
import com.pizzashop.specifications.PizzaSpecification;
import com.pizzashop.specifications.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Bartosz Pigla on 09/12/2016.
 */
@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "http://localhost:63342")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    //@CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public List<Product> read(
            @RequestParam(value = "pageSize", required = true) Integer pageSize,
            @RequestParam(value = "pageNumber", required = true) Integer pageNumber
    ) {
        List<Product> products=
                productRepository
                .findAll(new PageRequest((pageNumber == null) ? 0 : pageNumber - 1, pageSize))
                .getContent();


        return products;
    }

    @RequestMapping(value = "/read/{productId}", method = RequestMethod.GET)
    public Product read(@PathVariable Integer productId) {

        Product p = productRepository.findOne(productId);
        return p;
    }

    @RequestMapping(value = "/count", method = RequestMethod.POST)
    public Long count(
            @RequestBody ProductFilter productFilter
    ) {
        Specification<Product> productSpecification=new ProductSpecification<>(productFilter);
        return
                productRepository
                        .count(productSpecification);
    }

    @RequestMapping(value = "/read", method = RequestMethod.POST)
    public List<Product> read(
            @RequestBody ProductFilter productFilter,
            @RequestParam(value = "pageSize", required = true) Integer pageSize,
            @RequestParam(value = "pageNumber", required = true) Integer pageNumber
    ) {
        Specification<Product> productSpecification=new ProductSpecification<>(productFilter);
        List<Product> products=
                productRepository
                        .findAll(productSpecification, new PageRequest((pageNumber == null) ? 0 : pageNumber - 1, pageSize)).getContent();

        return products;
    }

//    @RequestMapping(value = "/read", method = RequestMethod.POST)
//    public List<Product> read(
//            @RequestBody ProductFilter productFilter,
//            @RequestParam(value = "pageSize", required = true) Integer pageSize,
//            @RequestParam(value = "pageNumber", required = true) Integer pageNumber
//    ) {
//        Specification<Product> productSpecification=new ProductSpecification<>(productFilter);
//        List<Product> products=
//                productRepository
//                        .findAll(productSpecification, new PageRequest((pageNumber == null) ? 0 : pageNumber - 1, pageSize)).getContent();
//
//        return products;
//    }

    @RequestMapping(value = "/delete/{productId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer productId) {
        Product product = productRepository.findOne(productId);
        product.setArchival(true);
        productRepository.save(product);
    }

    //@CrossOrigin(origins = "http://localhost:63342")
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long count(){
        return productRepository.count();
    }

}
