package com.fooddelivery.components.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DeliveryManDto {
    @NotNull @Email
    private String email;
    @NotNull
    private String password;
}
