package com.llh.springbootdemo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @author llh
 */
public class MyMetaObjectHandler implements MetaObjectHandler {
    private static final String CREATE_TIME = "createTime";
    private static final String UPDATE_TIME = "updateTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        // 自动填入创建时间
        if (metaObject.hasGetter(CREATE_TIME)) {
            this.fillStrategy(metaObject, CREATE_TIME, LocalDateTime.now());
        }

        // 自动填入更新时间
        if (metaObject.hasGetter(UPDATE_TIME)) {
            this.fillStrategy(metaObject, UPDATE_TIME, LocalDateTime.now());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 自动填入更新时间
        if (metaObject.hasGetter(UPDATE_TIME)) {
            this.fillStrategy(metaObject, UPDATE_TIME, LocalDateTime.now());
        }
    }
}