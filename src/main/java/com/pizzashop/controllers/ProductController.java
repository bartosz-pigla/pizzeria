package com.pizzashop.controllers;

import com.pizzashop.models.Product;
import com.pizzashop.repositories.ProductBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by barte on 09/12/2016.
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductBaseRepository productRepository;

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

        Product p = (Product)productRepository.findOne(productId);
        return p;
    }

    @RequestMapping(value = "/delete/{productId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer productId) {
        productRepository.delete(productId);
    }

}
