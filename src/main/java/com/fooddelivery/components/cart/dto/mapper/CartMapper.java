package com.fooddelivery.components.cart.dto.mapper;


import com.fooddelivery.components.cart.dto.CartDto;
import com.fooddelivery.components.cart.dto.CartItemDto;
import com.fooddelivery.components.cart.entity.Cart;
import com.fooddelivery.components.cart.entity.CartItem;
import com.fooddelivery.components.items.dto.mapper.ItemMapper;

import java.util.stream.Collectors;

public class CartMapper {

    public static CartItemDto toDto(CartItem item){
        return CartItemDto.builder()
                .id(item.getId())
                .quantity(item.getQuantity())
                .itemDto(item.getItem()!=null? ItemMapper.toDto(item.getItem()):null)
                .build();
    } 

    public static CartDto toDto(Cart cart){
        return CartDto.builder()
                .id(cart.getId())
                .items(cart.getCartItems().stream().map(i -> toDto(i)).collect(Collectors.toList()))
                .totalAmount(cart.getCartItems().stream()
                        .map(i-> (i.getItem().getPrice() * i.getQuantity()))
                        .mapToDouble(Double::doubleValue).sum())
                .build();
    }

}
