package com.fooddelivery.controller;

import com.fooddelivery.BaseTest;
import com.fooddelivery.components.user.dto.UserDto;
import com.fooddelivery.components.user.dto.UserUpdateDto;
import com.fooddelivery.components.user.entity.User;
import com.fooddelivery.components.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest extends BaseTest {

    @MockBean
    private UserService userService;

    @Test
    public void test_registerUserRole_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doReturn(new UserDto()).when(userService).registerUser(new UserDto(),"ADMIN");
        mvc.perform(MockMvcRequestBuilders.post("/admin/ADMIN")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new UserDto())))
                .andExpect(status().isOk());
    }

//    @Test
//    public void test_registerUser_ko() throws Exception{
//        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_CUSTOMER"));
//        doReturn(new UserDto()).when(userService).registerUser(any(),any());
//        mvc.perform(MockMvcRequestBuilders.post("/users/admin/ADMIN")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(new UserDto())))
//                .andExpect(status().is4xxClientError());
//    }

    @Test
    public void test_updateUser_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doReturn(new UserUpdateDto()).when(userService).updateUser(any(),any());
        mvc.perform(MockMvcRequestBuilders.put("/admin/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new UserUpdateDto())))
                .andExpect(status().isOk());
    }

    @Test
    public void test_getUserById_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doReturn(new User()).when(userService).findById(any());
        mvc.perform(MockMvcRequestBuilders.get("/admin/user/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void test_getAllUsers_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doReturn(new UserDto()).when(userService.getUsers());
        mvc.perform(MockMvcRequestBuilders.get("/admin/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(new UserDto())))
                .andExpect(status().isOk());
    }

}
