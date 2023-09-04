package com.example.seataorder.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "product_order")
public class Order {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long id;

    private Long userId;
    private Long productId;
    private Integer purchaseNumber;
    private Integer purchaseMoney;
}
