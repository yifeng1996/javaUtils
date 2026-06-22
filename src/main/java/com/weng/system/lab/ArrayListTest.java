package com.weng.system.lab;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.weng.system.entity.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  yifeng
 * @ CreateDate    :  2019/9/24 11:28
 * @ Version       :  1.0
 */
public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<Test> list = new ArrayList<>();
        list.add(new Test(1L,"aaa"));
        list.add(new Test(1L,"aaa"));
        list.add(new Test(1L,"bbb"));
        list.add(new Test(1L,"bbb"));
        list.add(new Test(1L,"ccc"));
        List<String> repeatList = list.stream().map(Test::getElement).collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b)) // 获得元素出现频率的 Map，键为元素，值为元素出现的次数
                .entrySet().stream()    // Set<Entry>转换为Stream<Entry>
                .filter(entry -> entry.getValue() > 1)  // 过滤出元素出现次数大于 1 的 entry
                .map(Map.Entry::getKey) // 获得 entry 的键（重复元素）对应的 Stream
                .collect(Collectors.toList());  // 转化为 List
        System.out.println(repeatList);
        for (String repeat : repeatList){
            int i = 1;
            for (Test test : list){
                if (repeat.equals(test.getElement())){
                    test.setElement(test.getElement() + i);
                    i++;
                }
            }
        }
        System.out.println(list);
        List<Integer>[] arrayList = new List[4];

    }
}
