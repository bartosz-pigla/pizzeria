package com.pizzashop.repositories;

import com.pizzashop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * Created by barte on 09/12/2016.
 */
@NoRepositoryBean
public interface ProductBaseRepository<T extends Product> extends JpaRepository<T, Integer> {
    @Query("SELECT distinct product.name FROM Product product")
    List<String> findNames();
}
