package com.fooddelivery.components.items.repository;

import com.fooddelivery.components.items.entity.Category;
import com.fooddelivery.components.items.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(name = "item_findItemByCategory")
    List<Item> findItemByCategoryId(Integer categoryId);
}
