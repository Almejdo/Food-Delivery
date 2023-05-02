package com.fooddelivery.components.items.service.impl;

import com.fooddelivery.components.items.dto.ItemCategoryDto;
import com.fooddelivery.components.items.entity.Category;
import com.fooddelivery.components.items.repository.CategoryRepository;
import com.fooddelivery.components.items.dto.ItemDto;
import com.fooddelivery.components.items.dto.mapper.ItemMapper;
import com.fooddelivery.components.items.entity.Item;
import com.fooddelivery.components.items.repository.ItemRepository;
import com.fooddelivery.components.items.service.ItemService;
import com.fooddelivery.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;
    @Override
    public Item findById(Integer itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(()->new ResourceNotFoundException(
                        String.format("Item with id %s not found",itemId)));
    }

    @Override
    public List<ItemDto> getItem() {
        return itemRepository.findAll()
                .stream()
                .map(i -> ItemMapper.toDto(i))
                .collect(Collectors.toList());
    }

    @Override
    public ItemDto addItem(ItemDto itemDto) {
        return ItemMapper.toDto(itemRepository.save(ItemMapper.toEntity(itemDto)));
    }

    @Override
    public ItemDto updateItem(Integer itemId, ItemDto req) {
        Item i = itemRepository.findById(itemId).orElseThrow(()-> new ResourceNotFoundException(String
                .format("Item with id %s not found",itemId)));;
         return ItemMapper.toDto(itemRepository
                 .save(ItemMapper.buildUpdateItem(i,req)));
    }
    @Override
    public List<ItemDto> findItemByCategoryId(Integer categoryId) {
        return categoryRepository.findItemByCategoryId(categoryId)
                .stream().map(ItemMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteItem(Integer itemId) {
//        Item i = itemRepository.findById(itemId)
//                .orElseThrow(()-> new ResourceNotFoundException(String
//                        .format("Item with id %s not found",itemId)));
//        itemRepository.delete(i);
//    return null;

        if(itemRepository.findById(itemId) == null){
            throw new ResourceNotFoundException(String.format("Item with id %s not found",itemId));
        }
        else
            itemRepository.deleteById(itemId);
    }

    public List<ItemCategoryDto> getCategories() {
        return categoryRepository.findAll().stream()
                .map(ItemMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Void deleteCategory(Integer id) {
        Category cat = categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(String
                        .format("category with id %s not found",id)));
        categoryRepository.delete(cat);
        return null;
    }

    @Override
    public ItemCategoryDto getCategoriesById(Integer id) {
        return categoryRepository.findById(id)
                .map(ItemMapper::toDto)
                .orElseThrow(()-> new ResourceNotFoundException(String
                        .format("Category with id %s not found",id)));
    }

    @Override
    public ItemCategoryDto addCategory(ItemCategoryDto req) {
        Category c = categoryRepository.save(ItemMapper.toEntity(req));
        return ItemMapper.toDto(c);
    }

    @Override
    public ItemCategoryDto updateCategory(Integer catId,ItemCategoryDto req) {
        Category category = categoryRepository.findById(catId)
                .orElseThrow(()-> new ResourceNotFoundException(String
                        .format("Category with id %s not found",catId)));
        return ItemMapper.toDto(categoryRepository
                .save(ItemMapper.buildUpdateCategory(category,req)));
    }
}
