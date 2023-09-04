package com.example.seataaccount.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Account {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long id;

    private String username;
    private Integer money;
}
