package com.fooddelivery.components.items.service;


import com.fooddelivery.components.items.dto.ItemCategoryDto;
import com.fooddelivery.components.items.dto.ItemDto;
import com.fooddelivery.components.items.entity.Item;

import java.util.List;

public interface ItemService {
Item findById(Integer itemId);
List<ItemDto> getItem();
ItemDto addItem(ItemDto itemDto);
List<ItemDto> findItemByCategoryId(Integer categoryId);
ItemDto updateItem(Integer itemId, ItemDto req);
Void deleteItem(Integer itemId);
ItemCategoryDto getCategoriesById(Integer id);
ItemCategoryDto addCategory(ItemCategoryDto req);
List<ItemCategoryDto> getCategories();
Void deleteCategory(Integer id);
ItemCategoryDto updateCategory(Integer catId, ItemCategoryDto req);
}
