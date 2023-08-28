package com.example.idservice.gen;


import com.example.idservice.common.Result;

public interface IDGen {
    Result get(String key);
    boolean init();
}
