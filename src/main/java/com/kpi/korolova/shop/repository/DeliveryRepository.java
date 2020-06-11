package com.kpi.korolova.shop.repository;

import com.kpi.korolova.shop.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Integer> {
    List<Delivery> findAllByDeleted(boolean deleted);
}
