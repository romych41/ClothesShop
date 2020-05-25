package com.kpi.korolova.shop.repository;

import com.kpi.korolova.shop.entities.Order;
import com.kpi.korolova.shop.model.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order getOrderByNumber(int number);
    List<Order> getOrdersByCustomerId(int customerId);
    Page<Order> getAllByStatus(OrderStatus status, Pageable pageable);
    boolean existsOrderByNumber(int number);
}
