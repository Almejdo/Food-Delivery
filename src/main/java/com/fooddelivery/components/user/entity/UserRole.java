package com.fooddelivery.components.user.entity;


import com.fooddelivery.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum UserRole {
    CUSTOMER("CUSTOMER"),
    ADMIN("ADMIN"),
    CHEF("CHEF");
    private String value;

    public static UserRole fromValue(String userRole){
        return Arrays.asList(UserRole.values())
                .stream().filter(r -> r.value.equals(userRole))
                .findFirst()
                .orElseThrow(()-> new ResourceNotFoundException(String
                        .format("Role %s not found",userRole)));
    }

    public String getValue() {
        return value;
    }
}
