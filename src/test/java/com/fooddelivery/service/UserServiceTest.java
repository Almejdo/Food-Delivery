package com.fooddelivery.service;


import com.fooddelivery.BaseTest;
import com.fooddelivery.components.user.dto.UserDto;
import com.fooddelivery.components.user.dto.UserUpdateDto;
import com.fooddelivery.components.user.dto.mapper.UserMapper;
import com.fooddelivery.components.user.entity.User;
import com.fooddelivery.components.user.repository.UserRepository;
import com.fooddelivery.components.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService toTest;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder encoder;

    @Test
    public void test_findById_ok(){
        Mockito.doReturn(Optional.of(new User())).when(userRepository).findById(Mockito.anyInt());
        User out = toTest.findById(1);
        assertNotNull(out);
    }
//
//    @Test
//    public void test_findById_ko(){
//        Mockito.doThrow(new ResourceNotFountException("user not found"))
//                .when(userRepository).findById(Mockito.anyInt());
//        Throwable throwable = assertThrows(Throwable.class,()-> toTest.findById(1));
//        assertEquals(ResourceNotFountException.class,throwable.getClass());
//    }
//
    @Test
    public void test_registerUser_ok(){
        Mockito.doReturn("anyPass").when(encoder).encode(Mockito.anyString());
        Mockito.doReturn(new User()).when(userRepository).save(Mockito.any());
        UserDto out = toTest.registerUser(new UserDto(),"CUSTOMER");
        assertNotNull(out);

    }
    @Test
    public void test_updateUser_ok(){
        Mockito.doReturn(Optional.of(new User())).when(userRepository).findById(Mockito.anyInt());
        User out = UserMapper.buildUpdateUser(new User(),new UserUpdateDto());
        assertNotNull(out);
    }
    @Test
    public void test_loadUserByUsername_ok(){
        Mockito.doReturn(Optional.of(new User())).when(userRepository).findByUsername(Mockito.anyString());
        UserDetails out = toTest.loadUserByUsername(Mockito.anyString());
        assertNotNull(out);
    }

    @Test
    public void test_getUsers_ok(){
        UserDto user1 = new UserDto();
        user1.setUsername("user1");
        user1.setEmail("user1@example.com");

        UserDto user2 = new UserDto();
        user2.setUsername("user2");
        user2.setEmail("user2@example.com");

        List<UserDto> userList = new ArrayList<>();

        userList.add(user1);
        userList.add(user2);

        Mockito.when(toTest.getUsers()).thenReturn(userList);
        List<UserDto> u = toTest.getUsers();
        assertNotNull(u);
    }
}
