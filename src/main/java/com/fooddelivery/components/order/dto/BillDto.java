package com.fooddelivery.components.order.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BillDto {

    private Integer id;


    private LocalDate date;


    private String customerName;


    private List<OrderItemDto> items;


    private double totalAmount;
}
