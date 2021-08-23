package com.llh.xxl.job.job;

import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class SampleXxlJob {
    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("demoJobHandler")
    public void demoJobHandler() throws Exception {
        log.info("开始执行任务，{}", LocalDateTime.now());

        // 模拟执行任务，比如查询所有用户，给用户发送短信
        for (int i = 0; i < 5; i++) {
            log.info("执行任务： " + i);

            // 有关数据库等操作一定要延迟，否则数据库连接资源会瞬间被占满
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
