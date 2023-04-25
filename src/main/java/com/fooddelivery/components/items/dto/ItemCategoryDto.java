package com.fooddelivery.components.items.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemCategoryDto {
    private Integer id;
    private String name;


}
