package com.weng.system.mq.producer;

import com.weng.system.entity.Test;
import com.weng.system.mq.constant.RocketMQConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName YuqingTraditionalmediaMark
 * @Description: 舆情服务传统数据打标
 * @Author weng.yifeng
 * @Date 2020/11/24 16:06
 * @Version V1.0
 **/
@Slf4j
@Component
public class YuqingTraditionalmediaProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void syncSend() {
        for (int j = 0; j < 5; j++) {
            Test test = new Test((long) j, "test测试：" + j);
            // 发送消息
            log.info("发送mq数据 {}, cost = {}ms", test);
            rocketMQTemplate.syncSend(RocketMQConstant.TOPIC_JAVA_UTILS_BATCH_TEST, test);
        }
    }
}