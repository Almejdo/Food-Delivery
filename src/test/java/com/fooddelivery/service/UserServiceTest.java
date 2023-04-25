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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

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
        User out = toTest.loadUserByUsername(Mockito.anyString());
        assertNotNull(out);
    }
}
