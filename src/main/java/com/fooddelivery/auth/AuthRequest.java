package com.fooddelivery.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class AuthRequest {
    @NotNull @Email
    private String email;
    @NotNull
    private String password;
}
