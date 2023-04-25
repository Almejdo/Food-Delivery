package com.fooddelivery.controller;


import com.fooddelivery.BaseTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.jwt.JwtEncoder;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest  extends BaseTest {

    @MockBean
    private  AuthenticationManager authenticationManager;

    @MockBean
    private  JwtEncoder jwtEncoder;

//TODO nshpi

//    @Test
//    public void test_login_ok() throws Exception{
//        Authentication auth = new UsernamePasswordAuthenticationToken("email@email.com","passi");
//        Mockito.doReturn(auth).when(authenticationManager).authenticate(Mockito.any());
//        Mockito.doReturn("tokenEncoded")
//                .when(jwtEncoder).encode(Mockito.any()).getTokenValue();
//
//        mvc.perform(MockMvcRequestBuilders.post("/auth/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(new TokenDTO())))
//                .andExpect(status().isOk());
//    }
}
