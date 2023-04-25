package com.fooddelivery.components.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryDetailsDto {
    private String address;
    private Integer phoneNumber;
}
