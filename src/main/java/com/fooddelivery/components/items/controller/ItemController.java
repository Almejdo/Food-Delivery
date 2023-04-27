package com.fooddelivery.components.items.controller;


import com.fooddelivery.components.items.dto.ItemCategoryDto;
import com.fooddelivery.components.items.dto.ItemDto;
import com.fooddelivery.components.items.dto.mapper.ItemMapper;
import com.fooddelivery.components.items.entity.Item;
import com.fooddelivery.components.items.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;


    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDto> getItemById(@PathVariable Integer itemId){
        Item i = itemService.findById(itemId);
        return ResponseEntity.ok(ItemMapper.toDto(i));
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> getItems(){
        return ResponseEntity.ok(itemService.getItem());
    }

    @RolesAllowed("CHEF")
    @PostMapping("/chef")
    public ResponseEntity<ItemDto> addItem(@RequestBody ItemDto item){
        return ResponseEntity.ok(itemService.addItem(item));
    }

    @RolesAllowed("CHEF")
    @PutMapping("/chef/{itemId}")
    public ResponseEntity<ItemDto> updateItem(@PathVariable Integer itemId,@RequestBody ItemDto req ){
        return ResponseEntity.ok(itemService.updateItem(itemId,req));
    }
    @GetMapping("/category/{categoryId}/list")
    public ResponseEntity<List<ItemDto>> getItemsByCategoryId(@PathVariable Integer categoryId){
        return ResponseEntity.ok(itemService.findItemByCategoryId(categoryId));
    }
    @RolesAllowed("CHEF")
    @DeleteMapping("/chef/{itemId}")
    public ResponseEntity<Void> deleteItemById(@PathVariable Integer itemId){
        itemService.deleteItem(itemId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/category")
    public ResponseEntity<List<ItemCategoryDto>>  getCategories(){
        return ResponseEntity.ok(itemService.getCategories());
    }

    @GetMapping("/category/{catId}")
    public ResponseEntity<ItemCategoryDto> getCategoryById(@PathVariable Integer catId){
        return ResponseEntity.ok(itemService.getCategoriesById(catId));
    }
    @RolesAllowed("CHEF")
    @PostMapping("/chef/category")
    public ResponseEntity<ItemCategoryDto> addCategory(@RequestBody ItemCategoryDto req){
        return ResponseEntity.ok(itemService.addCategory(req));
    }
    @RolesAllowed("CHEF")
    @PutMapping("/chef/category/{catId}")
    public ResponseEntity<ItemCategoryDto> updateCategory(@PathVariable Integer catId, @RequestBody ItemCategoryDto req){
        return ResponseEntity.ok(itemService.updateCategory(catId,req));
    }
    @RolesAllowed("CHEF")
    @DeleteMapping("/chef/category/{catId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer catId){
        itemService.deleteCategory(catId);
        return null;
    }


}
