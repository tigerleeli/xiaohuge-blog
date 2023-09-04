package com.example.seataorder.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity(name = "product_order")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private Long productId;
    private Integer purchaseNumber;
    private Integer purchaseMoney;
}
