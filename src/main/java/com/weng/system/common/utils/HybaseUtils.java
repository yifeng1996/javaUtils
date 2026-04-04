package com.weng.system.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;


/**
 * 海贝工具类
 *
 * @author weng.yifeng
 * @version 1.0
 * @since 2020/11/12 14:23
 */
public class HybaseUtils {

    /**
     * @Description: 表达式格式化
     * @param sql
     * @Author: weng.yifeng
     * @Date: 2021/3/25 11:16
     **/
    public static String formatSQL(String sql) {
        if (StringUtils.isNotBlank(StringUtils.trim(sql))) {
            String op = "+()\"&|\\-\\s";
            sql = sql.replaceAll("&+|\\+", "&");
            sql = sql.replaceAll("\\|+", "|");
            sql = sql.replace("!", "-");
            sql = sql.replace("\"", "");
            sql = sql.replaceAll("([" + op + "]*)([^" + op + "]+)([" + op + "]*)", "$1 \"$2\" $3");
            sql = sql.trim();
        }
        return sql;
    }

    /**
     * @Description: 表达式转成海贝语句
     * @param sql
     * @Author: weng.yifeng
     * @Date: 2021/4/2 9:47
     **/
    public static String sqlToHyBase(String sql){
        if(StringUtils.isBlank(sql)){
            return "";
        }
        sql = sql.replaceAll("\\|", " OR ")
                .replaceAll("&", " AND ")
                .replaceAll("-", " NOT ");
        return "(" + sql + ")";
    }

    public static String keywordToHyBase(String keyword,Integer lengthNum){
        if(StringUtils.isBlank(keyword)||lengthNum==null){
            return "";
        }
        keyword = keyword.replaceAll("\\|+", " OR ")
                .replaceAll("&+|\\+", " AND ")
                .replaceAll("-", " NOT ");
        keyword = "\""+ keyword+ "\"~"+ lengthNum;
        return keyword;
    }

    public static void main(String[] args){
        String sql = formatSQL("测试||温州&&测试");
        String sqlToHyBase = sqlToHyBase(sql);
        System.out.println(sql);
        System.out.println(sqlToHyBase);
        System.out.println(keywordToHyBase("测试||温州+测试", 10));

        /*List<String> strings = Arrays.asList("1###8###12,1###8###14", "1", "5,1", "5###12,1", "31###12,12");
        String regex = "(.*,)*(1)(###[0-9]+)*(,.*)*";
        String regex1 = "(1)(###[0-9]+)*";
        for (String string : strings) {
            String[] split = string.split(",");
            boolean res = Arrays.stream(split).anyMatch(e -> Pattern.matches(regex1, e));
            boolean matches = Pattern.matches(regex, string);
            System.out.println(res);
            System.out.println(matches);
            System.out.println("--------------");
        }*/
    }


}
