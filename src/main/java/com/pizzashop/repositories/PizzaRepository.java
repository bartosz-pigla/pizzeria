package com.pizzashop.repositories;

import com.pizzashop.models.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * Created by barte on 08/12/2016.
 */
@Transactional
public interface PizzaRepository extends ProductBaseRepository<Pizza>,JpaSpecificationExecutor<Pizza> {
    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Page<Pizza> findAll(Pageable pageRequest);

    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Pizza> findAll();

    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Pizza> findAll(Specification<Pizza> specification);

    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Pizza save(Pizza pizza);
}
