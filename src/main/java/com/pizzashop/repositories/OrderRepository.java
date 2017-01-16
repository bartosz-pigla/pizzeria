package com.pizzashop.repositories;

import com.pizzashop.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by barte on 10/12/2016.
 */
public interface OrderRepository extends JpaRepository<Order, Integer>{
}
