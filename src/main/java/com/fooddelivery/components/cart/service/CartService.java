package com.fooddelivery.components.cart.service;



import com.fooddelivery.components.cart.dto.CartDto;
import com.fooddelivery.components.cart.dto.CartItemRequest;

public interface CartService {

    CartDto getCart();
    CartDto addCartItem(CartItemRequest item);
    CartDto removeItem(Integer itemId);
}
