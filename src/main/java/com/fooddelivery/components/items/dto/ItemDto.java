package com.fooddelivery.components.items.dto;


import lombok.*;

import java.math.BigDecimal;

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
