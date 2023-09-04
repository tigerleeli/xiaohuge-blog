package com.example.seatabusiness.controller;

import com.example.seatabusiness.service.BusinessService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("business")
public class BusinessController {

    @Resource
    private BusinessService businessService;

    @GetMapping("purchase")
    public String purchase() {
        businessService.purchase();
        return "操作成功";
    }
}
