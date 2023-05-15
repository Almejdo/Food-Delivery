package com.fooddelivery.components.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Integer id;
    @NotNull(message = " Name is mandatory")
    private String name;
    @NotNull(message = " UserName is mandatory")
    private String username;
    @NotNull(message = "Phone Number is required")
    private Integer phoneNumber;

    @NotNull(message = "Email is necessary for registration")
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",message = "Please provide a valid email address")
    private String email;
    @NotNull(message = "Password is required")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$" ,
            message = "Password must contain at least: one digit [0-9].\n" +
                    ",one lowercase Latin character [a-z].\n" +
                    ", uppercase Latin character [A-Z].\n" +
                    ",one special character like ! @ # & ( ).\n" +
                    "Password must contain a length of at least 8 characters and a maximum of 20 characters.")

    private String password;
    @NotNull(message = "Address is required")
    private String address;

    private LocalDateTime createdAt;

}
