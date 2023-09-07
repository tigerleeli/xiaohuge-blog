package com.example.shardingproxydemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shardingproxydemo.entity.Company;
import com.example.shardingproxydemo.mapper.CompanyMapper;
import com.example.shardingproxydemo.service.CompanyService;
import org.springframework.stereotype.Service;

@Service("companyService")
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {
}
