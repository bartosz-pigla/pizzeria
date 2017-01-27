package com.pizzashop.repositories;

import com.pizzashop.models.Pizza;
import com.pizzashop.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * Created by Bartosz Pigla on 09/12/2016.
 */
@Transactional
public interface ProductRepository extends ProductBaseRepository<Product> {
    Page<Product> findAll(Pageable pageRequest);

    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Product save(Product product);


    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Page<Product> findAll(Specification<Product> specification, Pageable pageable);


    Long count(Specification<Product> specification);

}