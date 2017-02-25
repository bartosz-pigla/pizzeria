package com.pizzashop.repositories;

import com.pizzashop.models.Manager;
import com.pizzashop.models.OrderPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by bartek on 2/25/17.
 */
public interface ManagerRepository  extends JpaRepository<OrderPosition,Integer> {
    @Query("SELECT manager FROM Manager manager WHERE manager.eMail=:eMail")
    Manager findByeMail(@Param("eMail")String eMail);
}
