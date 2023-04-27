package com.fooddelivery.components.cart.controller;


import com.fooddelivery.components.cart.dto.CartDto;
import com.fooddelivery.components.cart.dto.CartItemRequest;
import com.fooddelivery.components.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService ;

    @GetMapping()
    public ResponseEntity<CartDto> getCart(){
        return ResponseEntity.ok(cartService.getCart());
    }

    @PostMapping("/addItem")
    public ResponseEntity<CartDto> addCartItem(@RequestBody CartItemRequest req) {
        return ResponseEntity.ok(cartService.addCartItem(req));
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<CartDto> removeCartItem(@PathVariable Integer itemId){
        return ResponseEntity.ok(cartService.removeItem(itemId));
    }


}
