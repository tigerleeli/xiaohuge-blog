package com.example.shardingproxydemo.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "id-service")
public interface IdService {

    @GetMapping("/id/generate")
    Long generateId(@RequestParam String key);
}
