package com.fooddelivery.components.cart.entity;

import com.fooddelivery.components.items.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cart_Item")
@AllArgsConstructor
@NoArgsConstructor

@Data
@Builder
public class CartItem {



    @Id
    @GeneratedValue
    private Integer id;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "cart_id",referencedColumnName = "id")
    private Cart cart;
    @ManyToOne
    @JoinColumn(name = "item_id",referencedColumnName = "id")
    private Item item;


}
