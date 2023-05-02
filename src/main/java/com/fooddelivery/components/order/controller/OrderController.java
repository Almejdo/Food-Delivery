package com.fooddelivery.components.order.controller;


import com.fooddelivery.components.order.dto.BillDto;
import com.fooddelivery.components.order.dto.DeliveryDetailsDto;
import com.fooddelivery.components.order.dto.OrderDto;
import com.fooddelivery.components.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;


    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Integer id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
    @GetMapping("/user/{email}")
    public ResponseEntity<List<OrderDto>> findOrderOfUserId(@PathVariable String email){
        return ResponseEntity.ok(orderService.findAllByUserEmail(email));
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
    @RolesAllowed("CHEF")
    @PostMapping("/{orderId}/to/{deliveryManId}")
    public ResponseEntity<Void> setOrderToDeliveryMan(@PathVariable Integer orderId,@PathVariable Integer deliveryManId){
        orderService.setOrderToDeliveryMan(deliveryManId,orderId);
        return ResponseEntity.noContent().build();
    }
    @RolesAllowed("CHEF")
    @GetMapping("/delivery-man/{deliveryManId}")
    public ResponseEntity<List<OrderDto>> getOrdersByDeliveryMan(@PathVariable Integer deliveryManId){

        return ResponseEntity.ok(orderService.getOrdersByDeliveryMan(deliveryManId));
  }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@AuthenticationPrincipal Jwt jwt, @RequestBody DeliveryDetailsDto d){
        return ResponseEntity.ok(orderService.processOrder(jwt,d));
    }

    @RolesAllowed("CHEF")
    @PutMapping("/{orderId}/{orderStatus}")
    public ResponseEntity<Void> setOrderStatus(@PathVariable Integer orderId,@PathVariable String orderStatus){
        return ResponseEntity.ok(orderService.setDeliveryStatus(orderId,orderStatus));
    }
    @GetMapping("/bill")
    public ResponseEntity<BillDto> generateBill(@AuthenticationPrincipal Jwt jwt){
        return ResponseEntity.ok(orderService.generateBill(jwt));
    }
    @RolesAllowed("CHEF")
    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders(){
        return ResponseEntity.ok(orderService.getOrders());
    }
}

