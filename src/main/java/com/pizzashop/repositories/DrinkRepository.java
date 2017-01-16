package com.pizzashop.repositories;

import com.pizzashop.models.Drink;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.QueryHint;

/**
 * Created by barte on 09/12/2016.
 */
@Transactional
public interface DrinkRepository extends ProductBaseRepository<Drink>,JpaSpecificationExecutor<Drink> {
    Page<Drink> findAll(Pageable pageRequest);

    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Drink save(Drink drink);
}
