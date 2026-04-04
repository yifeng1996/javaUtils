package com.weng.system.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.weng.system.entity.Test;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  yifeng
 * @ CreateDate    :  2019/9/24 11:28
 * @ Version       :  1.0
 */
public class ArrayListTest1 {
    // 温州及其区县
    public static final String[] ALL_WENZHOU_ARR = {"330300","330302","330303","330304","330305","330324","330326","330327","330328","330329","330381","330382","330383"};

    public static void main(String[] args) {
        /*long l = 0;
        for (int i = 0; i < 4; i++){
            System.out.println(l++);
        }*/
        List<String> infoClass = new ArrayList<>();
        String baseTYVO = "{\"class2\":\"利益群体\",\"class1\":\"社会民生\",\"probability\":0.9000775218009949,\"id\":174,\"keyword\":\"强征|强制征用|霸占|贱卖|非法占用|钉子户|强拆|跑路|圈钱|暴雷|欺骗|诈骗|官员站台|一刀切|恶意清退|上访|堵路|围堵|冲突|打砸|维权|拉横幅|烂尾|质量问题|偷工减料|延期交付|降价销售|欠薪|讨薪|拖欠|克扣|不发工资|上访|堵路|围堵|冲突|打砸|维权|拉横幅|诈骗之都|诈骗省\"}";
        if (StringUtils.isNotBlank(baseTYVO)) {
            JSONArray jsonArray = new JSONArray();
            for (String s : baseTYVO.split(";")) {
                if (StringUtils.isEmpty(s)) {
                    continue;
                }
                boolean validArray = JSON.isValidArray(s);
                if (validArray) {
                    jsonArray.addAll(JSONArray.parseArray(s));
                }
                boolean validObject = JSON.isValidObject(s);
                if (validObject) {
                    jsonArray.add(JSONObject.parseObject(s));
                }
            }
            for (int i = 0; i < jsonArray.size(); i++) {
                infoClass.add(jsonArray.getJSONObject(i).getString("class1"));
                infoClass.add(jsonArray.getJSONObject(i).getString("class2"));
            }
        }
        List<String> collect = infoClass.stream().filter(item -> !StringUtils.isBlank(item)).distinct().collect(Collectors.toList());
        System.out.println(collect);
//        System.out.println(getRegionCode("330302;330300"));
    }

    public static String getRegionCode(String regionCode) {
        if (StringUtils.isBlank(regionCode)){
            return regionCode;
        }
        String[] regionCodeArr = StringUtils.split(regionCode, ";");
        String[] allWenzhouArr = ALL_WENZHOU_ARR;
        List<String> regionCodeList = new ArrayList<>(Arrays.asList(regionCodeArr));
        List<String> allWenzhouList = new ArrayList<>(Arrays.asList(allWenzhouArr));
        regionCodeList.retainAll(allWenzhouList);
        if (CollectionUtils.isEmpty(regionCodeList)){
            return "";
        }
        return Joiner.on(";").join(regionCodeList);
    }
}
