package com.weng.system.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


/**
 * 正则工具类
 *
 * @author weng.yifeng
 * @version 1.0
 * @since 2020/11/12 14:23
 */
public class RegexUtils {

    /**
     * @Description: 表达式是否有误，true有误，false无误
     * @param sql
     * @Author: weng.yifeng
     * @Date: 2021/3/25 11:16
     **/
    public static boolean regexSQL(String sql) {
        String regex = ".*((\\|\\|+)|(&&+)|(\\|&+)|(&\\|+)).*";
        boolean matches = Pattern.matches(regex, sql);
        return matches;
    }
    public static boolean regexSQLTwo(String sql) {
        if (StringUtils.isBlank(sql)){
            return false;
        }
        int count = 0;
        for (int i = 0; i < sql.length(); i++) {
            char c = sql.charAt(i);
            if (c == '(') {
                count++;
            } else if (c == ')') {
                if (count == 0) {
                    return false;
                } else {
                    count--;
                }
            }
        }
        return count == 0;
    }

    private static void regexTest(){
        List<String> strings = Arrays.asList("1###8###12,1###8###14", "1", "5,1", "5###12,1", "31###12,12");
        String regex = "(.*,)*(1)(###[0-9]+)*(,.*)*";
        String regex1 = "(1)(###[0-9]+)*";
        for (String string : strings) {
            String[] split = string.split(",");
            boolean res = Arrays.stream(split).anyMatch(e -> Pattern.matches(regex1, e));
            boolean matches = Pattern.matches(regex, string);
            System.out.println(res);
            System.out.println(matches);
            System.out.println("--------------");
        }
    }

    public static void main(String[] args){
        String sql = "(杭州|嘉兴|湖州)";
        System.out.println(regexSQLTwo(sql));

    }


}
