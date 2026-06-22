package com.weng.system.lab;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  yifeng
 * @ CreateDate    :  2019/9/24 11:28
 * @ Version       :  1.0
 */
public class ArrayListTest2 {

    public static void main(String[] args) {
        /*List<Long> newAreaTenantIdList = new ArrayList<>();
        newAreaTenantIdList.add(1L);
        newAreaTenantIdList.add(2L);
        newAreaTenantIdList.add(3L);
        List<Long> oldAreaTenantIdList = new ArrayList<>();
        oldAreaTenantIdList.add(1L);
        oldAreaTenantIdList.add(2L);
        oldAreaTenantIdList.add(3L);
        oldAreaTenantIdList.add(4L);

        List<Long> oldAreaTenantIdOne = new ArrayList<>(oldAreaTenantIdList);
        List<Long> newAreaTenantIdOne = new ArrayList<>(newAreaTenantIdList);
        oldAreaTenantIdOne.removeAll(newAreaTenantIdList);
        newAreaTenantIdOne.removeAll(oldAreaTenantIdList);
        System.out.println(oldAreaTenantIdOne);
        System.out.println(newAreaTenantIdOne);*/

        List<String> hitKeywords = new ArrayList<>();
        hitKeywords.add("广东FFc");
        hitKeywords.add("浙江aab");
        hitKeywords.add("杭州you");
        hitKeywords.add("杭州");
        List<String> hitKeywordList = new ArrayList<>(hitKeywords);
        for (String hitKeyword : hitKeywordList) {
            String upperCase = StringUtils.upperCase(hitKeyword);
            if (!hitKeyword.equals(upperCase)){
                hitKeywords.add(upperCase);
            }
            String lowerCase = StringUtils.lowerCase(hitKeyword);
            if (!hitKeyword.equals(lowerCase)){
                hitKeywords.add(lowerCase);
            }
        }
        System.out.println(hitKeywords);
    }
}
