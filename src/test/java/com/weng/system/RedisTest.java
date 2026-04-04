package com.weng.system;

import com.weng.system.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

/**
 * @ClassName RedisTest
 * @Description: TODO
 * @Author weng.yifeng
 * @Date 2022/8/8 16:56
 * @Version V1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void setNxTest() {
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                    boolean aBoolean = redisService.setNx("setNxTest:123",  24 * 3600L);
//                boolean aBoolean = redisService.setIfAbsent("setNxTest:123", "1",  24 * 3600L);
//                Boolean aBoolean = redisService.hasKey("setNxTest:123");
                if (aBoolean) {
//                    redisService.set("setNxTest:123", "1", 24 * 3600L);
                }
                if (aBoolean){
                    System.out.println("Thread：" + 0);
                }else {
                    System.out.println("Thread：" + 1);
                }

            }).start();
        }
    }

}
