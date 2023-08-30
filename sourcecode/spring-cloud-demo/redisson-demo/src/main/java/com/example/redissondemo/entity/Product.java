package com.example.redissondemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Product {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer number;
}
