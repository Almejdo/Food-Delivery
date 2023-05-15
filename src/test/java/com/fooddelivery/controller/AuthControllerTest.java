package com.fooddelivery.controller;


import com.fooddelivery.BaseTest;
import com.fooddelivery.auth.AuthRequest;
import com.fooddelivery.components.user.dto.UserDto;
import com.fooddelivery.components.user.dto.UserUpdateDto;
import com.fooddelivery.components.user.entity.User;
import com.fooddelivery.components.user.repository.UserRepository;
import com.fooddelivery.components.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest  extends BaseTest {

    @MockBean
    private  AuthenticationManager authenticationManager;
    @MockBean
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private  JwtEncoder jwtEncoder;

    @Test
    public void test_login_ok() throws Exception{
        Authentication auth = Mockito.mock(UsernamePasswordAuthenticationToken.class);
        Mockito.doReturn(auth).when(authenticationManager).authenticate(Mockito.any());
        User fakeUser = new User();
        fakeUser.setEmail("userm@email.com");
        Mockito.doReturn(fakeUser).when(auth).getPrincipal();
        Mockito.doReturn(Arrays.asList(new SimpleGrantedAuthority("test")))
                .when(auth).getAuthorities();

        Jwt fakeJwt = Mockito.mock(Jwt.class);
        Mockito.doReturn(fakeJwt).when(jwtEncoder).encode(Mockito.any());
        Mockito.doReturn("Bearer ").when(fakeJwt).getTokenValue();

        mvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new AuthRequest("user@email.com","password"))))
                .andExpect(status().isOk());
    }
    @Test
    public void test_registerUser_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_ADMIN"));
        doReturn(new UserDto()).when(userService).registerUser(any(),any());
        mvc.perform(MockMvcRequestBuilders.put("/admin/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new UserDto())))
                .andExpect(status().isOk());
    }

}
