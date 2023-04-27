package com.fooddelivery.components.items.dto;


import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDto {

    private Integer id;

    private String name;
    private String picture;

    private ItemCategoryDto categoryDto;

    private String description;

    private Double price;





}
