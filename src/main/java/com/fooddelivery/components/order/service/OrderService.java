package com.fooddelivery.components.order.service;

import com.fooddelivery.components.order.dto.BillDto;
import com.fooddelivery.components.order.dto.DeliveryDetailsDto;
import com.fooddelivery.components.order.dto.OrderDto;
import com.fooddelivery.components.order.entity.Delivery;
import com.fooddelivery.components.order.entity.Order;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

public interface OrderService {
   OrderDto getOrderById(Integer id);
   Void deleteOrder(Integer id);
   OrderDto findOrderOfUserId(Integer userId);

   Void setDeliveryStatus(Integer deliveryId, String status);
   List<OrderDto> getOrdersByDeliveryMan(Integer deliveryManId);
   Void setOrderToDeliveryMan(Integer deliveryManId,Integer orderId);
   OrderDto processOrder(Jwt jwt, DeliveryDetailsDto details);
   Delivery addDelivery(DeliveryDetailsDto req);
   BillDto generateBill(Integer userId);

}
