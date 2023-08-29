package com.example.shardingjdbcdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shardingjdbcdemo.entity.Company;
import com.example.shardingjdbcdemo.mapper.CompanyMapper;
import com.example.shardingjdbcdemo.service.CompanyService;
import org.springframework.stereotype.Service;

@Service("companyService")
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {
}
