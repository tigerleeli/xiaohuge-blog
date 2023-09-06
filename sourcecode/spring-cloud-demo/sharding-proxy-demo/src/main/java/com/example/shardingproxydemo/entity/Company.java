package com.example.shardingproxydemo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Company {
    private Long id;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
