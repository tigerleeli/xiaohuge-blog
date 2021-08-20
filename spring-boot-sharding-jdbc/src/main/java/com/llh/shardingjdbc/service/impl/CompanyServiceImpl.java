package com.llh.shardingjdbc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llh.shardingjdbc.entity.Company;
import com.llh.shardingjdbc.mapper.CompanyMapper;
import com.llh.shardingjdbc.service.CompanyService;
import org.springframework.stereotype.Service;

/**
 * @author 小虎哥的技术博客
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {
}
