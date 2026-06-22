package com.weng.system.lab;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ApplicationContextTest {
    public static void main(String[] args) {
        ApplicationContext context  = new AnnotationConfigApplicationContext(ApplicationContextTest.class);  //加载appContext.xml文件
        String[] beanNames = context.getBeanNamesForType(Date.class);  //返回所有JavaBean的名称
        Object bean = context.getBean(beanNames[0]);
        for(String name:beanNames){
            System.out.println("JavaBean名称:"+name);
        }
    }
}
