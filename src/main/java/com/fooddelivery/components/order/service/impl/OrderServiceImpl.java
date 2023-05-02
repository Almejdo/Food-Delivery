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
    private static final Integer MAX_ORDERS = 4;



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
    public List<OrderDto> findAllByUserEmail(String email) {
        return  orderRepository.findAllByCustomerEmail(email)
                .stream().map(OrderMapper::toDto).collect(Collectors.toList());
    }


    public void setOrderToDeliveryMan(Integer deliveryManId,Integer orderId) {
        DeliveryMan deliveryMan = deliveryManRepository.findById(deliveryManId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("DeliveryMan with id %s not found",deliveryManId)));
        List<Order> orders = deliveryMan.getOrders();
        if (orders.size() >= MAX_ORDERS) {
            throw new DeliveryManMaxOrdersReachedException();
        }
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Order with id %s not found",orderId)));
        order.setDeliveryMan(deliveryMan);
        deliveryMan.getOrders().add(order);
        deliveryManRepository.save(deliveryMan);
        orderRepository.save(order);

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
        deliveryRepository.findById(deliveryId)
                .map(d -> {
                    d.setDeliveryStatus(DeliveryStatus.fromValue(status));
                    return  deliveryRepository.save(d);
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
    @Override
    public BillDto generateBill(Jwt jwt) {
        User u = userService.getUserFromToken(jwt);
        Bill b = OrderMapper.generateBill(u);
        return OrderMapper.billDto(b);
    }

    @Override
    public List<OrderDto> getOrders() {
        return orderRepository.findAll()
                .stream()
                .map(p -> OrderMapper.toDto(p))
                .collect(Collectors.toList());
    }




    }



