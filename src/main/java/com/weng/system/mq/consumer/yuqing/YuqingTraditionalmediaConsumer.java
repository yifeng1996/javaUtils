package com.weng.system.mq.consumer.yuqing;

import com.weng.system.mq.constant.RocketMQConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

/**
 * 消费态势感知推送预警数据
 */
@Slf4j
@Component
@RocketMQMessageListener(
        nameServer = "${rocketmq.nameserver}",
        consumerGroup = RocketMQConstant.TOPIC_JAVA_UTILS_BATCH_TEST_GROUP,
        topic = RocketMQConstant.TOPIC_JAVA_UTILS_BATCH_TEST,
        consumeMode = ConsumeMode.ORDERLY,
        selectorType = SelectorType.TAG
        // 消费 tag 的数据
//        selectorExpression = "tag"
)
public class YuqingTraditionalmediaConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {
    @Override
    public void onMessage(String message) {
        log.info("消费数据处理，消息为{}", message);
        /*try {
            Long startTime = System.currentTimeMillis();
            List<Test> tests = JSON.parseArray(message, Test.class);
            for (Test test : tests) {
                log.info("消费数据处理后的实体为{}", test);
            }
            log.info("消费数据处理：耗时{}ms", (System.currentTimeMillis() - startTime));
        } catch (Exception e) {
            log.error("消费数据处理异常，消息为{}", message, e);
        }*/
    }

    /**
     * 对消费者客户端的一些配置
     * 重写prepareStart方法
     * @param defaultMQPushConsumer
     */
    @Override
    public void prepareStart(DefaultMQPushConsumer defaultMQPushConsumer) {
        //设置每次消息拉取的时间间隔 单位 毫秒
        defaultMQPushConsumer.setPullInterval(1000);
        //最小消费线程池数
        defaultMQPushConsumer.setConsumeThreadMin(1);
        //最大消费线程池数
        defaultMQPushConsumer.setConsumeThreadMax(10);
        //设置消费者单次批量消费的消息数目上限    默认1
        defaultMQPushConsumer.setConsumeMessageBatchMaxSize(3);
        //设置每个队列每次拉取的最大消费数
        defaultMQPushConsumer.setPullBatchSize(16);
    }

}
