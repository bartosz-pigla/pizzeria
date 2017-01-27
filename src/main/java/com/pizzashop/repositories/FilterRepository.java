package com.pizzashop.repositories;

import com.pizzashop.models.Product;
import com.pizzashop.repositories.customRepositories.FilterRepositoryCustom;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Bartosz Pigla on 31/12/2016.
 */
@Transactional
public interface FilterRepository extends ProductBaseRepository<Product>, FilterRepositoryCustom{
}
