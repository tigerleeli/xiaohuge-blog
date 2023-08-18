package com.llh.netty.service.impl;

import com.llh.netty.Application;
import com.llh.netty.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {
    private final Logger logger = LoggerFactory.getLogger(Application.class);

    @Override
    public void save(String msg) {
        logger.info("保存数据: {}", msg);
    }
}
