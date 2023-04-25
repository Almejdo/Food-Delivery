package com.fooddelivery.components.order.entity;


import com.fooddelivery.components.items.entity.Item;
import com.fooddelivery.components.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@NamedQueries({
        @NamedQuery(name = "findOrderOfUserId",
                query = "SELECT o FROM Order o WHERE o.user.id = :user_id")
})

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue
    private Integer id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    private String customerName;
    private String customerEmail;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deliveryMan_id",referencedColumnName = "id")
    private DeliveryMan deliveryMan;

    private Double totalAmount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id",referencedColumnName = "id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @CreatedDate
    private LocalDateTime createdAt;

    }
