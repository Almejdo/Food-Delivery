package com.fooddelivery.components.cart.dto;

import com.fooddelivery.components.items.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {

    private Integer id;
    private Integer quantity;
    private ItemDto itemDto;
}
