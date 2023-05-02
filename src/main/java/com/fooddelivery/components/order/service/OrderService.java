package com.fooddelivery.components.order.service;

import com.fooddelivery.components.order.dto.BillDto;
import com.fooddelivery.components.order.dto.DeliveryDetailsDto;
import com.fooddelivery.components.order.dto.OrderDto;
import com.fooddelivery.components.order.entity.Delivery;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface OrderService {
   OrderDto getOrderById(Integer id);
   Void deleteOrder(Integer id);
   List<OrderDto> findAllByUserEmail(String email);

   Void setDeliveryStatus(Integer deliveryId, String status);
   List<OrderDto> getOrdersByDeliveryMan(Integer deliveryManId);
    void setOrderToDeliveryMan(Integer deliveryManId,Integer orderId);
   OrderDto processOrder(Jwt jwt, DeliveryDetailsDto details);
   Delivery addDelivery(DeliveryDetailsDto req);
   BillDto generateBill(Jwt jwt);
   List<OrderDto> getOrders();

}
