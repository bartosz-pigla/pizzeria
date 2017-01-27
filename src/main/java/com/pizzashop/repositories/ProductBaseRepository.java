package com.pizzashop.repositories;

import com.pizzashop.models.Pizza;
import com.pizzashop.models.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * Created by Bartosz Pigla on 09/12/2016.
 */
@NoRepositoryBean
public interface ProductBaseRepository<T extends Product> extends JpaRepository<T, Integer> {
    @Query("SELECT distinct product.name FROM Product product")
    List<String> findNames();
}
