package com.fooddelivery.components.cart.repository;


import com.fooddelivery.components.cart.entity.Cart;
import com.fooddelivery.components.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
}
