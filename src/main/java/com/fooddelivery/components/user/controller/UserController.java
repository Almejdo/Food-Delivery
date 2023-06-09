package com.fooddelivery.components.user.controller;


import com.fooddelivery.components.user.dto.UserDto;
import com.fooddelivery.components.user.dto.UserUpdateDto;
import com.fooddelivery.components.user.dto.mapper.UserMapper;
import com.fooddelivery.components.user.entity.User;
import com.fooddelivery.components.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class UserController {

    private final UserService userService;

    @RolesAllowed("ADMIN")
    @PostMapping("/{userRole}")
    public ResponseEntity<UserDto> registerUserRole(@RequestBody @Valid UserDto req, @PathVariable String userRole){
        UserDto dto = userService.registerUser(req,userRole);
        return ResponseEntity.ok(dto);
    }

    @RolesAllowed("ADMIN")
    @PutMapping("/user/{id}")
    public ResponseEntity<UserUpdateDto> updateUser(@PathVariable Integer id, @RequestBody UserUpdateDto req){
        UserUpdateDto u = userService.updateUser(id,req);
        return ResponseEntity.ok(u);
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id){
        User u = userService.findById(id);
        return ResponseEntity.ok(UserMapper.toDto(u));
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/user")
    public ResponseEntity<List<UserDto>> getAllUsers(){
       return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping()
    public ResponseEntity<User> getUserFromToken(@AuthenticationPrincipal Jwt jwt){
        return ResponseEntity.ok(userService.getUserFromToken(jwt));
    }

}
