package com.pizzashop.repositories;

import com.pizzashop.models.Complaint;
import com.pizzashop.models.Pizza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Bartosz Pigla on 10/12/2016.
 */
@Transactional
public interface ComplaintRepository extends JpaRepository<Complaint,Integer> {
    Page<Complaint> findAll(Pageable pageRequest);
}
