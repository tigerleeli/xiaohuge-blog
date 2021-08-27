package com.llh.shardingjdbc.controller;

import com.llh.shardingjdbc.entity.Company;
import com.llh.shardingjdbc.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;


@RestController
@RequestMapping("company")
public class CompanyController {
    @Resource
    private CompanyService companyService;

    @PostMapping("add")
    public Boolean add(@RequestBody Company company) {
        company.setCreateTime(LocalDateTime.now());
        company.setUpdateTime(LocalDateTime.now());
        return companyService.save(company);
    }

    @GetMapping("get")
    public Company get(@RequestParam Long id) {
        return companyService.getById(id);
    }
}
