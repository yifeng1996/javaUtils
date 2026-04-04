package com.weng.system;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName ListTest
 * @Description: TODO
 * @Author weng.yifeng
 * @Date 2020/4/18 12:48
 * @Version V1.0
 **/
public class ListTest {
    @Test
    public void test1(){
        List<Integer> soruceLibBindList = Arrays.asList(58,60);
        for (Integer soruceId : soruceLibBindList) {
            System.out.println(soruceId);
        }
    }
}
