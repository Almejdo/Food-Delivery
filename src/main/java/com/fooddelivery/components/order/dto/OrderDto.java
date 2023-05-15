package com.fooddelivery.components.order.dto;

import com.fooddelivery.components.items.dto.ItemDto;
import com.fooddelivery.components.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDto {
    private Integer id;
    private List<OrderItemDto> items;
    private String customerName;
    private String customerEmail;
    private String orderStatus;
    private Integer customerPhoneNumber;
    private String customerAddress;

}
