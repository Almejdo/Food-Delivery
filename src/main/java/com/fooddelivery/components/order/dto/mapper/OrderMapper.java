package com.fooddelivery.components.order.dto.mapper;

import com.fooddelivery.components.cart.entity.CartItem;
import com.fooddelivery.components.items.dto.mapper.ItemMapper;
import com.fooddelivery.components.order.dto.BillDto;
import com.fooddelivery.components.order.dto.OrderDto;
import com.fooddelivery.components.order.dto.OrderItemDto;
import com.fooddelivery.components.order.entity.Bill;
import com.fooddelivery.components.order.entity.Order;
import com.fooddelivery.components.order.entity.OrderItem;
import com.fooddelivery.components.user.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDto toDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .customerEmail(order.getCustomerEmail())
                .customerName(order.getCustomerName())
                .orderStatus(order.getDeliveryStatus().getValue())
                .items(order.getOrderItems()!=null?order.getOrderItems().stream()
                        .map(OrderMapper::toDto).collect(Collectors.toList()) : null)

                 .build();
    }


    public static Order buildOrder(User u, Order o) {
        o.setCustomerName(u.getName());
        o.setCustomerEmail(u.getEmail());
        List<OrderItem> items = u.getCart().getCartItems().stream().map(item -> {
            OrderItem i = new OrderItem();
            i.setPrice(getPrice(item));
            i.setQuantity(item.getQuantity());
            i.setItem(item.getItem());
            i.setOrder(o);
            return i;
        }).collect(Collectors.toList());
        o.setOrderItems(items);
        return o;
    }


    private static Double getPrice(CartItem item) {
        if (item.getItem() != null)
            return item.getItem().getPrice();
        else return null;

    }
    public static OrderItemDto toDto(OrderItem item){
        return OrderItemDto.builder()
                .id(item.getId())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .itemDto(item.getItem()!=null?ItemMapper.toDto(item.getItem()):null)
                .build();
    }
    public static Bill generateBill(User u) {
        return Bill.builder()
                .id(u.getId())
                .date(LocalDate.now())
        .customerName(u.getName())
                .items(u.getCart().getCartItems().stream().map(item -> {
                    OrderItem i = new OrderItem();
                    i.setQuantity(item.getQuantity());
                    i.setItem(item.getItem());
                    i.setPrice(getPrice(item));
                    return i;
                }).collect(Collectors.toList()))
                .totalAmount(u.getCart().getCartItems().stream()
                .map(i-> (i.getItem().getPrice() * i.getQuantity()))
                .mapToDouble(Double::doubleValue).sum())
        .build();
    }

    public static BillDto billDto(Bill b){
        return BillDto.builder()
                .date(LocalDate.now())
                .id(b.getId())
                .customerName(b.getCustomerName())
                .items(b.getItems()!=null?b.getItems().stream()
                        .map(OrderMapper::toDto).collect(Collectors.toList()) : null)
                .totalAmount(b.getTotalAmount())
                .build();
    }

}
