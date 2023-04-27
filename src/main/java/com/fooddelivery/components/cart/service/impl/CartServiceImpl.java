package com.fooddelivery.components.cart.service.impl;


import com.fooddelivery.components.cart.dto.CartDto;
import com.fooddelivery.components.cart.dto.CartItemRequest;
import com.fooddelivery.components.cart.dto.mapper.CartMapper;
import com.fooddelivery.components.cart.entity.Cart;
import com.fooddelivery.components.cart.entity.CartItem;
import com.fooddelivery.components.cart.repository.CartItemRepository;
import com.fooddelivery.components.cart.repository.CartRepository;
import com.fooddelivery.components.cart.service.CartService;
import com.fooddelivery.components.items.repository.ItemRepository;
import com.fooddelivery.components.user.entity.User;
import com.fooddelivery.components.user.service.UserService;
import com.fooddelivery.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.fooddelivery.config.SecurityConfig.getJwt;
@Slf4j
@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {
    private final UserService userService;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    @Override
    public CartDto getCart() {
        return CartMapper.toDto(userService.getUserFromToken(getJwt()).getCart());
    }

    @Override
    public CartDto addCartItem(CartItemRequest item) {
        User user = userService.getUserFromToken(getJwt());
        Cart userCart = user.getCart();
        CartItem itemToAdd;
        if(userCart==null){
            userCart = new Cart();
            userCart.setUser(user);
            userCart = cartRepository.save(userCart);
            itemToAdd = buildItem(userCart,item);
        }else {
            itemToAdd = buildItem(userCart,item);
        }
        userCart.getCartItems().add(itemToAdd);
        Cart cart = cartRepository.save(userCart);
        return CartMapper.toDto(cartRepository.findById(userCart.getId()).get());
    }

    @Override
    public CartDto removeItem(Integer itemId) {
        User user = userService.getUserFromToken(getJwt());
        cartItemRepository.deleteById(itemId);
        return CartMapper.toDto(cartRepository
                .findById(user.getCart().getId()).get());
    }

    private CartItem buildItem(Cart cart, CartItemRequest req) {
        CartItem item = new CartItem();
        item.setCart(cart);
        item.setQuantity(req.getQuantity());
        if (req.getItemId() != null) {
            item.setItem(itemRepository.findById(req.getItemId())
                    .orElseThrow(() -> new ResourceNotFoundException(String
                            .format("Item with id %s not found", req.getItemId()))));
        }
        return item;

    }
}
