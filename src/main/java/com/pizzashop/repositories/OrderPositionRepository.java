package com.pizzashop.repositories;

import com.pizzashop.models.OrderPosition;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Bartosz Pigla on 10/12/2016.
 */
public interface OrderPositionRepository extends JpaRepository<OrderPosition,Integer>{
}
