package com.fooddelivery.components.items.dto.mapper;


import com.fooddelivery.components.items.dto.ItemCategoryDto;
import com.fooddelivery.components.items.entity.Category;
import com.fooddelivery.components.items.dto.ItemDto;
import com.fooddelivery.components.items.entity.Item;

public class ItemMapper {

    public static ItemDto toDto(Item i){
        return ItemDto.builder()
                .name(i.getName())
                .price(i.getPrice())
                .description(i.getDescription())
                .id(i.getId())
                .picture(i.getPicture())
                .categoryDto(i.getCategory()!=null?toDto(i.getCategory()):null)
                .build();
    }

    public static Item toEntity(ItemDto i){
        return Item.builder()
                .price(i.getPrice())
                .name(i.getName())
                .picture(i.getPicture())
                .id(i.getId())
                .category(i.getCategoryDto()!=null?toEntity(i.getCategoryDto()):null)
                .description(i.getDescription())
                .build();
    }

    public static Item buildUpdateItem(Item i,ItemDto req){
        i.setName(req.getName());
        i.setDescription(req.getDescription());
        i.setPicture(req.getPicture());
        i.setPrice(req.getPrice());
        return i;
    }
    public static ItemCategoryDto toDto(Category c){
        return ItemCategoryDto.builder()
                .id(c.getId())
                .name(c.getName())
                .build();
    }

    public static Category toEntity(ItemCategoryDto c){
        return Category.builder()
                .name(c.getName())
                .id(c.getId())
                .build();
    }
    public static Category buildUpdateCategory(Category c,ItemCategoryDto req){
        c.setName(req.getName());
        return c;
    }
}
