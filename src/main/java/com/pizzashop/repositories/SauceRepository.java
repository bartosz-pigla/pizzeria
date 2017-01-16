package com.pizzashop.repositories;

import com.pizzashop.models.Sauce;
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
public interface SauceRepository extends ProductBaseRepository<Sauce>,JpaSpecificationExecutor<Sauce> {
    Page<Sauce> findAll(Pageable pageRequest);

    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true")})
    Sauce save(Sauce sauce);
}
