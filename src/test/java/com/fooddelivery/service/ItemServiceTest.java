package com.fooddelivery.service;


import com.fooddelivery.BaseTest;
import com.fooddelivery.components.items.dto.ItemCategoryDto;
import com.fooddelivery.components.items.dto.ItemDto;
import com.fooddelivery.components.items.entity.Category;
import com.fooddelivery.components.items.entity.Item;
import com.fooddelivery.components.items.repository.CategoryRepository;
import com.fooddelivery.components.items.repository.ItemRepository;
import com.fooddelivery.components.items.service.ItemService;
import com.fooddelivery.components.user.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ItemServiceTest extends BaseTest {

    @Autowired
    private ItemService toTest;

    @MockBean
    private ItemRepository itemRepository;

    @MockBean
    private CategoryRepository categoryRepository;



    @Test
    public void test_findItemById_ok(){
        Mockito.doReturn(Optional.of(new Item())).when(itemRepository).findById(Mockito.anyInt());
        Item out = toTest.findById(1);
        assertNotNull(out);
    }

    @Test
    public void test_addItem_ok() {
        Mockito.doReturn(new Item()).when(itemRepository).save(Mockito.any());
        ItemDto i = toTest.addItem(new ItemDto());
        assertNotNull(i);
    }

    @Test
    public void test_addCategory_ok(){
        Mockito.doReturn(new Category()).when(categoryRepository).save(Mockito.any());
        ItemCategoryDto i = toTest.addCategory(new ItemCategoryDto());
        assertNotNull(i);
    }

    @Test
    public void test_findCategoryById_ok(){
        Mockito.doReturn(Optional.of(new Category())).when(categoryRepository).findById(Mockito.anyInt());
        List<ItemDto> out = toTest.findItemByCategoryId(1);
        assertNotNull(out);

    }

}
