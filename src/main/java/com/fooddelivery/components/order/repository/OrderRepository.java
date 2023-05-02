package com.fooddelivery.components.order.repository;

import com.fooddelivery.components.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findAllByCustomerEmail(String email);

}
