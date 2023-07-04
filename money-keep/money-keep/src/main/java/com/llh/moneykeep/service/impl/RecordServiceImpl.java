package com.llh.moneykeep.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.llh.moneykeep.entity.Record;
import com.llh.moneykeep.mapper.RecordMapper;
import com.llh.moneykeep.service.RecordService;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {

}
