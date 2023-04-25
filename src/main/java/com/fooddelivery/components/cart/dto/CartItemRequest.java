package com.fooddelivery.components.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequest {

    private Integer id;
    private Integer quantity;
    private Integer itemId;

}
