package com.kpi.korolova.shop.repository;

import com.kpi.korolova.shop.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByUserId(Integer userId);
}
