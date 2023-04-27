package com.fooddelivery.components.user.service;



import com.fooddelivery.components.order.dto.DeliveryManDto;
import com.fooddelivery.components.user.dto.UserDto;
import com.fooddelivery.components.user.dto.UserUpdateDto;
import com.fooddelivery.components.user.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface UserService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    User findById(Integer id);
    UserDto registerUser(UserDto req, String userRole);
    UserUpdateDto updateUser(Integer id, UserUpdateDto req);

    List<UserDto> getUsers();
    User loadUserByEmail(String email) ;
     User getUserFromToken(Jwt jwt);
    DeliveryManDto registerDeliveryMan(DeliveryManDto req, String userRole);
}
