package com.fooddelivery.controller;

import com.fooddelivery.BaseTest;
import com.fooddelivery.components.items.dto.ItemCategoryDto;
import com.fooddelivery.components.items.dto.ItemDto;
import com.fooddelivery.components.items.entity.Item;
import com.fooddelivery.components.items.service.ItemService;
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
public class ItemControllerTest extends BaseTest {


    @MockBean
    private ItemService itemService;


    @Test
    public void test_getItemById_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_CUSTOMER"));
        doReturn(new Item()).when(itemService).findById(any());
        mvc.perform(MockMvcRequestBuilders.get("/items/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void test_updateItem_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_CHEF"));
        doReturn(new ItemDto()).when(itemService).updateItem(any(),any());
        mvc.perform(MockMvcRequestBuilders.put("/items/chef/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new ItemDto())))
                .andExpect(status().isOk());
    }
    @Test
    public void test_getCategoryById_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_CUSTOMER"));
        doReturn(new ItemCategoryDto()).when(itemService).getCategoriesById(any());
        mvc.perform(MockMvcRequestBuilders.get("/items/category/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void test_updateCategory_ok() throws Exception{
        SecurityContextHolder.getContext().setAuthentication(getAuthentication("ROLE_CHEF"));
        doReturn(new ItemCategoryDto()).when(itemService).updateCategory(any(),any());
        mvc.perform(MockMvcRequestBuilders.put("/items/chef/category/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(new ItemCategoryDto())))
                .andExpect(status().isOk());
    }

}
