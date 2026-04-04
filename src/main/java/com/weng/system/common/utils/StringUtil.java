package com.weng.system.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName StringUtil
 * @Description: 字符串工具类
 * @Author weng.yifeng
 * @Date 2020/12/5 18:02
 * @Version V1.0
 **/
public class StringUtil {

    public static String handleString(String string){
        if (StringUtils.isBlank(string)){
            return string;
        }
        string = StringUtils.trim(string);
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < string.length(); i++){
            if (i == 0){
                if('(' != string.charAt(i)){
                    stringBuffer.append("\"").append(string.charAt(i));
                }
                continue;
            }
            if ('|' == string.charAt(i) || '&' == string.charAt(i) || '-' == string.charAt(i)){
                stringBuffer.append("\"").append(string.charAt(i)).append("\"");
                continue;
            }
            stringBuffer.append(string.charAt(i));
            if (i == string.length() - 1){
                if('(' != string.charAt(i)){
                    stringBuffer.append("\"");
                }
            }
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args){
        System.out.println(handleString(" sa&a|sd "));
    }
}
