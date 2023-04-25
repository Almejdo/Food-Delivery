package com.fooddelivery.components.items.repository;


import com.fooddelivery.components.items.entity.Item;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ItemCustomRepository{
    List<Item> findItemByCategoryId(Integer categoryId);
}
