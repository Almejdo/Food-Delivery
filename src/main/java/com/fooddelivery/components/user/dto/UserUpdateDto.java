package com.fooddelivery.components.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateDto {
    private Integer id;
    @NotNull
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotNull
    @NotBlank(message = "User Name is mandatory")
    private String username;
    @NotNull
    @NotBlank(message = "PhoneNumber is mandatory")
    private String phoneNumber;

    @NotNull
    @NotBlank(message = "Email is necessary for registration")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",message = "Please provide a valid email address")
    private String email;

}
