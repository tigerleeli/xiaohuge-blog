package com.example.idservice.controller;


import com.example.idservice.common.Result;
import com.example.idservice.gen.SegmentIDGenImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("id")
public class IdController {
    @Resource
    private SegmentIDGenImpl segmentIDGen;

    @GetMapping("generate")
    public Long generate(@Validated @NotBlank(message = "缺少参数：key") @RequestParam String key) {
        Result result = segmentIDGen.get(key);
        return result.getId();
    }
}
