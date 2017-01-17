package com.pizzashop.repositories;

import com.pizzashop.models.Ingredient;
import com.pizzashop.models.Rebate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * Created by barte on 10/12/2016.
 */
@Transactional
public interface RebateRepository extends JpaRepository<Rebate,Integer>{

    @Query("SELECT distinct rebate.name FROM Rebate rebate")
    List<String> findNames();

    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Rebate> findAll();
}
