package com.fooddelivery.components.order.service.impl;

import com.fooddelivery.components.order.dto.BillDto;
import com.fooddelivery.components.order.dto.DeliveryDetailsDto;
import com.fooddelivery.components.order.dto.OrderDto;
import com.fooddelivery.components.order.dto.mapper.OrderMapper;
import com.fooddelivery.components.order.entity.*;
import com.fooddelivery.components.order.repository.*;
import com.fooddelivery.components.order.service.OrderService;
import com.fooddelivery.components.user.entity.User;
import com.fooddelivery.components.user.service.UserService;
import com.fooddelivery.exception.DeliveryManMaxOrdersReachedException;
import com.fooddelivery.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    private final OrderCustomRepository orderCustomRepository;
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final DeliveryManRepository deliveryManRepository;
    private final DeliveryRepository deliveryRepository;
    private final BillRepository billRepository;

    @Override
    public OrderDto getOrderById(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));

        return OrderMapper.toDto(order);
    }


    public Void deleteOrder(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));

        orderRepository.delete(order);
        return null;
    }

    @Override
    public OrderDto findOrderOfUserId(Integer userId) {
        return OrderMapper.toDto(orderCustomRepository.findOrderOfUserId(userId)) ;
    }


    public Void setOrderToDeliveryMan(Integer deliveryManId,Integer orderId) {
        DeliveryMan deliveryMan = deliveryManRepository.findById(deliveryManId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("DeliveryMan with id %s not found",deliveryManId)));
        List<Order> orders = deliveryMan.getOrders();
        if (orders.size() >= deliveryMan.getMaxOrders()) {
            throw new DeliveryManMaxOrdersReachedException();
        }
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException(String.format("Order with id %s not found",orderId)));
        order.setDeliveryMan(deliveryMan);
        orderRepository.save(order);
        return null;
    }


    public List<OrderDto> getOrdersByDeliveryMan(Integer deliveryManId) {
        DeliveryMan deliveryMan = deliveryManRepository.findById(deliveryManId).orElseThrow(() -> new ResourceNotFoundException(String.format("DeliveryMan with id %s not found",deliveryManId)));
        List<Order> orders =deliveryMan.getOrders();
        return orders.stream()
                .map(o -> OrderMapper.toDto(o))
                .collect(Collectors.toList());
    }

    @Override
    public Void setDeliveryStatus(Integer deliveryId, String status) {
        deliveryManRepository.findById(deliveryId)
                .map(d -> {
                    d.setDeliveryStatus(DeliveryStatus.fromValue(status));
                    return  deliveryManRepository.save(d);
                });
        return null;
    }
    @Transactional
    @Override
    public OrderDto processOrder(Jwt jwt, DeliveryDetailsDto details) {
        User u = userService.getUserFromToken(jwt);
        Order o = orderRepository.save(new Order());
        o = OrderMapper.buildOrder(u,o);
        o.setTotalAmount(o.getOrderItems().stream().map(i->i.getPrice())
                .mapToDouble(Double::doubleValue).sum());
        o.setDeliveryStatus(DeliveryStatus.ON_THE_ROAD);

        Delivery delivery = addDelivery(details);
        o.setDelivery(delivery);
        o = orderRepository.save(o);

        return OrderMapper.toDto(o);
    }
    @Override
    public Delivery addDelivery(DeliveryDetailsDto req) {
        Delivery d = new Delivery();
        d.setDeliveryAdress(req.getAddress());
        d.setPhoneNumber(req.getPhoneNumber());
        d.setDeliveryDate(LocalDateTime.now());
        d.setDeliveryStatus(DeliveryStatus.PENDING);
        return deliveryRepository.save(d);

    }
    public BillDto generateBill(Integer userId) {
        Order order = orderCustomRepository.findOrderOfUserId(userId);
        if (order == null) {
            throw new RuntimeException("Order not found");
        }

        BillDto billDTO = new BillDto();
        billDTO.setItems(order.getOrderItems());
        billDTO.setCustomerName(order.getCustomerName());
        billDTO.setBillNumber("Bill:" + order.getId());

        Double totalAmount = order.getOrderItems().stream()
                .mapToDouble(orderDetail -> orderDetail.getItem().getPrice() * orderDetail.getQuantity())
                .sum();
        billDTO.setTotalAmount(totalAmount);

        return billDTO;
    }


    }



