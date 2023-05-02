package com.fooddelivery.controller;

import com.fooddelivery.BaseTest;
import com.fooddelivery.components.cart.dto.CartDto;
import com.fooddelivery.components.cart.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(locations = "classpath*:/spring/applicationContext*.xml")
public class CartControllerTest extends BaseTest {

    @MockBean
    private CartService cartService;


//    @Test
//    public void test_getCart_ok() throws Exception{
//        SecurityContextHolder.getContext();
//        doReturn(new CartDto()).when(cartService.getCart(String s));
//        mvc.perform(MockMvcRequestBuilders.get("/cart")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(new CartDto())))
//                .andExpect(status().isOk());
//    }
}
