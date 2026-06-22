package com.weng.system.lab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        List<Map<String,Object>> pageLdata = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("title","cestitle");
        map1.put("key","keyces");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("title","cestitle");
        map2.put("wenhao","wenhaoces");
        map2.put("ceshi","ceshi");
        pageLdata.add(map1);
        pageLdata.add(map2);
        System.out.println(pageLdata);
        for (Map<String,Object> map : pageLdata) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String newValue = entry.getValue().toString().replace("ces", "<span style=\"color:red\">ces</span>");
                map.put(entry.getKey(), newValue);
            }
        }
        System.out.println(pageLdata);
    }
}
