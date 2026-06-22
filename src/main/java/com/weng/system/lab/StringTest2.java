package com.weng.system.lab;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.google.common.base.Joiner;
import com.weng.system.common.utils.BusinessUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @ Description : java类作用描述
 * @ Author : yifeng
 * @ Date : 2019/10/15 14:33
 * @ Version : 1.0
 */
public class StringTest2 {
    public static void main(String[] args){
        /*String string = "  &lt;浙江 &gt;杭州\r <font color=red>人办实事</font>";
        String title = "&quot;衡水中学&quot;上市！在深、浙扩张受阻，&nbsp;家长怒喊“滚出去”";
        string = string.replaceAll("&lt;.*?&gt;", "").replaceAll("[　*|\\n|\\r|\n|\r]*", "").replaceAll("<font(.*?)>|</font>", "");
        title = title.replaceAll("&lt;.*?&gt;", "").replaceAll("[　*|\\n|\\r|\n|\r]*", "").replace("&nbsp;", " ").replace("&quot;", "\"");
        System.out.println(title);
        *//*String max = "234";
        max = String.format("%03d", Long.valueOf(max));
        System.out.println(max);
        int i = (int) ((double)(1 / 3))*100;
        System.out.println(i);*//*
        String deptName = "市直部门";
        String[] substring = StringUtils.split(deptName, "-");
        System.out.println(substring[0]);*/
        /*String dutyDept = "广东";
        String deptId = StringUtils.substringAfterLast(dutyDept, "/");
        String domainId = StringUtils.substringBefore(dutyDept, "/");
        System.out.println(deptId);
        System.out.println(domainId);*/

        /*List<String> collect = new ArrayList<>(Arrays.asList("8,10,200,220,"));
        String join = Joiner.on(StringPool.COMMA).join(collect);
        String[] uverifiedTypes = StringUtils.splitByWholeSeparatorPreserveAllTokens(join, StringPool.COMMA);
        System.out.println(uverifiedTypes);*/

        /*String dutyDept = "广东";
        System.out.println(StringUtils.upperCase(dutyDept));
        System.out.println(StringUtils.lowerCase(dutyDept));*/

        String thing = "#浙江<font color=\"red\">温州</font>一民房发生火灾致2人死亡#【#浙江<font color=\"red\">温州</font>龙港市一民房发生火灾致2人死亡#】";
        thing = "学习“习思想”，争做新青年#大学生活##习思想##团课##湖州师范学院#";
        // 使用正则表达式匹配 <font> 标签
        Pattern pattern = Pattern.compile("<font color=\"red\">(.*?)</font>");
        Pattern pattern1 = Pattern.compile("(?=<font color=\"red\">)|(?<=</font>)");
        String[] split = pattern1.split(thing);
        Matcher matcher = pattern.matcher(thing);
        while (matcher.find()){
            System.out.println(matcher.group());
        }
        System.out.println(Arrays.toString(split));

    }
}
