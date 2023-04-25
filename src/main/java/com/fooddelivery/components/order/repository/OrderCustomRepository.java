package com.fooddelivery.components.order.repository;

import com.fooddelivery.components.order.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderCustomRepository {
    Order findOrderOfUserId(Integer userId);
}
