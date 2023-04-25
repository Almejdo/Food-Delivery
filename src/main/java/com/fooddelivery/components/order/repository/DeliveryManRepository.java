package com.fooddelivery.components.order.repository;

import com.fooddelivery.components.order.entity.DeliveryMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryManRepository extends JpaRepository<DeliveryMan,Integer> {

}