package com.fooddelivery.components.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "delivery")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Delivery {

    @Id
    @GeneratedValue
    private Integer id;
    private String deliveryAdress;

    private Integer phoneNumber;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;
    private LocalDateTime deliveryDate;

}
