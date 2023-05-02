package com.fooddelivery.components.cart.service;



import com.fooddelivery.components.cart.dto.CartDto;
import com.fooddelivery.components.cart.dto.CartItemRequest;
import org.springframework.security.oauth2.jwt.Jwt;

public interface CartService {

    CartDto getCart();
    CartDto addCartItem(CartItemRequest item);
    CartDto removeItem(Integer itemId);
}
