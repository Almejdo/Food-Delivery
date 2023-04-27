package com.fooddelivery.components.user.service.impl;


import com.fooddelivery.components.order.dto.DeliveryManDto;
import com.fooddelivery.components.order.entity.DeliveryMan;
import com.fooddelivery.components.order.repository.DeliveryManRepository;
import com.fooddelivery.components.user.dto.UserDto;
import com.fooddelivery.components.user.dto.UserUpdateDto;
import com.fooddelivery.components.user.dto.mapper.UserMapper;
import com.fooddelivery.components.user.entity.User;
import com.fooddelivery.components.user.entity.UserRole;
import com.fooddelivery.components.user.repository.UserRepository;
import com.fooddelivery.components.user.service.UserService;
import com.fooddelivery.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final DeliveryManRepository deliveryManRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                format("User with username - %s, not found", username)));
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(String
                        .format("User with id %s not found",id)));
    }

    @Override
    public UserDto registerUser(UserDto req, String userRole) {
        User u = UserMapper.toEntity(req);
        u.setRole(userRole!=null? UserRole.fromValue(userRole):UserRole.ADMIN);
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        u = userRepository.save(u);
        return UserMapper.toDto(u);
    }

    @Override
    public UserUpdateDto updateUser(Integer id, UserUpdateDto req) {
        User u = findById(id);
        u = UserMapper.buildUpdateUser(u,req);
        return UserMapper.toUpdateDto(userRepository.save(u));
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(p -> UserMapper.toDto(p))
                .collect(Collectors.toList());
    }

    @Override
    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException(format("User with email- %s, not found",email)));
    }

//    @Override
//    public User loadUserByUsername(String username) throws UsernameNotFoundException {
//        return userRepository
//                .findByUsername(username)
//                .orElseThrow(
//                        () -> new UsernameNotFoundException(
//                                format("User with username - %s, not found", username)));
//    }
    @Override
    public User getUserFromToken(Jwt jwt) {
        String sub = (String) jwt.getClaims().get("sub");
        return userRepository.findByEmail(sub).get();
    }
    @Override
    public DeliveryManDto registerDeliveryMan(DeliveryManDto req, String userRole) {
        DeliveryMan d = UserMapper.deliveryManEntity(req);
        d.setRole(userRole!=null? UserRole.fromValue(userRole):UserRole.DELIVERY_MAN);
        d.setPassword(passwordEncoder.encode(req.getPassword()));
        d = deliveryManRepository.save(d);
        return UserMapper.deliveryManDto(d);
    }
}
