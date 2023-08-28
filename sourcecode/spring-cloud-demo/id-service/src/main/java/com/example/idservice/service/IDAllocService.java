package com.example.idservice.service;




import com.example.idservice.model.LeafAlloc;

import java.util.List;

public interface IDAllocService {
     List<LeafAlloc> getAllLeafAllocs();
     LeafAlloc updateMaxIdAndGetLeafAlloc(String tag);
     LeafAlloc updateMaxIdByCustomStepAndGetLeafAlloc(LeafAlloc leafAlloc);
     List<String> getAllTags();
}
