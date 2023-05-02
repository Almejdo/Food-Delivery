package com.fooddelivery.components.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "bill")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Bill {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "bill_number")
    private String billNumber;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "customer_name")
    private String customerName;

    @OneToMany(fetch = FetchType.LAZY)
    private List<OrderItem> items;

    @Column(name = "total_amount")
    private Double totalAmount;
}
