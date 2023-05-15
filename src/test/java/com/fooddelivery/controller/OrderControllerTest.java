package com.fooddelivery.controller;

import com.fooddelivery.BaseTest;
import com.fooddelivery.components.order.dto.OrderDto;
import com.fooddelivery.components.order.service.OrderService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest extends BaseTest {

    @MockBean
    private OrderService orderService;

    @Test
    public void test_getOrderById_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_CUSTOMER"));
        doReturn(new OrderDto()).when(orderService).getOrderById(any());
        mvc.perform(MockMvcRequestBuilders.get("/orders/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    @Disabled
    public void test_createOrder_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_CUSTOMER"));
        doReturn(new OrderDto()).when(orderService).processOrder(any());
        mvc.perform(MockMvcRequestBuilders.put("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new OrderDto())))
                .andExpect(status().isOk());
    }
}
