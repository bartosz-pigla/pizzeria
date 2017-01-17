package com.pizzashop.repositories;

import com.pizzashop.models.Ingredient;
import com.pizzashop.models.Seasoning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * Created by barte on 09/12/2016.
 */
@Transactional
public interface SeasoningRepository extends JpaRepository<Seasoning,Integer> {
    @Query("SELECT distinct seasoning.name FROM Seasoning seasoning")
    List<String> findNames();

    @QueryHints(value = { @QueryHint(name = "org.hibernate.cacheable", value = "true")})
    List<Seasoning> findAll();
}
