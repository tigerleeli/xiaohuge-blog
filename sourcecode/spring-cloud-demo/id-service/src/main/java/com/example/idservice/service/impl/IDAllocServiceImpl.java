package com.example.idservice.service.impl;

import com.example.idservice.mapper.IDAllocMapper;
import com.example.idservice.model.LeafAlloc;
import com.example.idservice.service.IDAllocService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IDAllocServiceImpl implements IDAllocService {

    @Resource
    private IDAllocMapper idAllocMapper;

    @Override
    public List<LeafAlloc> getAllLeafAllocs() {
        return idAllocMapper.getAllLeafAllocs();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LeafAlloc updateMaxIdAndGetLeafAlloc(String tag) {
        idAllocMapper.updateMaxId(tag);
        return idAllocMapper.getLeafAlloc(tag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LeafAlloc updateMaxIdByCustomStepAndGetLeafAlloc(LeafAlloc leafAlloc) {
        idAllocMapper.updateMaxIdByCustomStep(leafAlloc);
        return idAllocMapper.getLeafAlloc(leafAlloc.getKey());
    }

    @Override
    public List<String> getAllTags() {
        return idAllocMapper.getAllTags();
    }
}
