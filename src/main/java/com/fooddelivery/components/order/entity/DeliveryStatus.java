package com.fooddelivery.components.order.entity;

import com.fooddelivery.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;
@AllArgsConstructor
public enum DeliveryStatus {
    ON_THE_ROAD("ON_THE_ROAD"),
    READY("READY"),
    ARRIVED("ARRIVED"),
    PENDING("PENDING");

    private String value;

    public static DeliveryStatus fromValue(String value){
        return Arrays.asList(DeliveryStatus.values())
                .stream().filter(r -> r.value.equals(value))
                .findFirst()
                .orElseThrow(()-> new ResourceNotFoundException(String
                        .format("Shipping Status %s not found",value)));
    }

    public String getValue() {
        return value;
    }
}
