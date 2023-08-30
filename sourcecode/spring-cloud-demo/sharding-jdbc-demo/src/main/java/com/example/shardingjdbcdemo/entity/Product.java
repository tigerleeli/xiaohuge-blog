package com.example.shardingjdbcdemo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Product {
    private Long id;
    private Long companyId;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
