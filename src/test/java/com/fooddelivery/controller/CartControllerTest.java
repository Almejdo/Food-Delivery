package com.fooddelivery.controller;

import com.fooddelivery.BaseTest;
import com.fooddelivery.components.cart.dto.CartDto;
import com.fooddelivery.components.cart.service.CartService;
import com.fooddelivery.components.user.dto.UserUpdateDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest extends BaseTest {

    @MockBean
    private CartService cartService;


//    @Test
//    public void test_getCart_ok() throws Exception{
//        SecurityContextHolder.getContext();
//        doReturn(new CartDto()).when(cartService.getCart(any()));
//        mvc.perform(MockMvcRequestBuilders.get("/cart")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(new CartDto())))
//                .andExpect(status().isOk());
//    }
    @Test
    public void test_addCartItem_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_CUSTOMER"));
        doReturn(new CartDto()).when(cartService).addCartItem(any());
        mvc.perform(MockMvcRequestBuilders.put("/cart/addItem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new CartDto())))
                .andExpect(status().isOk());
    }
    @Test
    public void test_getCart_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_CUSTOMER"));
        doReturn(new CartDto()).when(cartService).getCart();
        mvc.perform(MockMvcRequestBuilders.put("/cart")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new CartDto())))
                .andExpect(status().isOk());
    }
}
