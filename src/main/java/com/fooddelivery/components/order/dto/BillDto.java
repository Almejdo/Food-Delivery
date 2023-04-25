package com.fooddelivery.components.order.dto;

import com.fooddelivery.components.order.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BillDto {
    private String billNumber;
    private LocalDate date;
    private String customerName;
    private List<OrderItem> items;
    private Double totalAmount;

}
