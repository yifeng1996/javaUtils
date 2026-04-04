package com.weng.system.common.runner;

import com.weng.system.mq.producer.YuqingTraditionalmediaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 启动回调
 */
@Slf4j
@Component
public class ApplicationStartupRunner implements ApplicationRunner {

    @Autowired
    private YuqingTraditionalmediaProducer yuqingTraditionalmediaProducer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        yuqingTraditionalmediaProducer.syncSend();
    }
}
