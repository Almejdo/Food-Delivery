package com.fooddelivery.components.order.repository.impl;

import com.fooddelivery.components.items.entity.Item;
import com.fooddelivery.components.order.entity.Order;
import com.fooddelivery.components.order.repository.DeliveryManRepository;
import com.fooddelivery.components.order.repository.OrderCustomRepository;
import com.fooddelivery.components.order.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderRepositoryCustomImpl implements OrderCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Order findOrderOfUserId(Integer userId) {
        return entityManager.createNamedQuery("findOrderOfUserId", Order.class)
                .setParameter("user_id",userId)
                .getSingleResult();
    }
}
