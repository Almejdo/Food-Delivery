package com.fooddelivery.components.order.dto;

import com.fooddelivery.components.items.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderItemDto {
    private Integer id;
    private Integer quantity;
    private Double price;
    private ItemDto itemDto;

}
