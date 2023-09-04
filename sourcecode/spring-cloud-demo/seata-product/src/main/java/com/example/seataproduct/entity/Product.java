package com.example.seataproduct.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Product {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long id;
    private String productName;
    private Integer productNumber;
}
