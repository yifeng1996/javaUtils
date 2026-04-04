package com.weng.system.common.utils;

import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @ Description   :  xml数据转成json格式
 * @ Author        :  yifeng
 * @ CreateDate    :  2019/9/23 10:47
 * @ Version       :  1.0
 */
public class XmlToJsonUtil {

    public static JSONObject xmltoJson(String xml) throws Exception {
        JSONObject jsonObject = new JSONObject();
        Document document = DocumentHelper.parseText(xml);
        //获取根节点元素对象
        Element root = document.getRootElement();
        iterateNodes(root, jsonObject);
        return jsonObject;
    }
    /**
     * 遍历元素
     * @param node 元素
     * @param json 将元素遍历完成之后放的JSON对象
     */
    @SuppressWarnings("unchecked")
    public static void iterateNodes(Element node,JSONObject json){
        //获取当前元素的名称
        String nodeName = node.getName();
        //判断已遍历的JSON中是否已经有了该元素的名称
        if(json.containsKey(nodeName)){
            //该元素在同级下有多个
            Object Object = json.get(nodeName);
            JSONArray array = null;
            if(Object instanceof JSONArray){
                array = (JSONArray) Object;
            }else {
                array = new JSONArray();
                array.add(Object);
            }
            //获取该元素下所有子元素
            List<Element> listElement = node.elements();
            //获取该元素下所有属性
            List<Attribute> attributes = node.attributes();
            JSONObject attributeMap = new JSONObject();
            for (Attribute attribute : attributes){
                String attributeName = attribute.getName();
                Object attributeData = attribute.getData();
                attributeMap.put(attributeName,attributeData);
            }
            if(listElement.isEmpty()){
                //该元素无子元素，获取元素的值
                array.add(attributeMap);
                json.put(nodeName, array);
                return ;
            }
            //有子元素
            JSONObject newJson = new JSONObject();
            //遍历所有子元素
            for(Element e:listElement){
                //递归
                iterateNodes(e,newJson);
            }
            newJson.putAll(attributeMap);
            array.add(newJson);
            json.put(nodeName, array);
            return ;
        }
        //该元素同级下第一次遍历
        //获取该元素下所有子元素
        List<Element> listElement = node.elements();
        //获取该元素下所有属性
        List<Attribute> attributes = node.attributes();
        JSONObject attributeMap = new JSONObject();
        for (Attribute attribute : attributes){
            String attributeName = attribute.getName();
            Object attributeData = attribute.getData();
            attributeMap.put(attributeName,attributeData);
        }
        if(listElement.isEmpty()){
            //该元素无子元素，获取元素的属性值
            json.put(nodeName, attributeMap);
            return ;
        }
        //有子节点，新建一个JSONObject来存储该节点下子节点的值
        JSONObject object = new JSONObject();
        //遍历所有一级子节点
        for(Element e:listElement){
            //递归
            iterateNodes(e,object);
        }
        attributeMap.putAll(object);
        json.put(nodeName, attributeMap);
        return ;
    }

    /**
     * 测试
     */
    public static void main(String[] args) throws Exception {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <数据 类型=\"城市AQI实时报\" 开始时间=\"2019-10-10 00\" 结束时间=\"2019-10-10 23\"><省 名称=\"浙江\"><地区 名称=\"杭州\" 编码=\"330100\"><城市 名称=\"杭州市\" 编码=\"330101\"><实况 时间=\"2019-10-10 03\" 空气质量状况=\"良\" 空气质量等级=\"二级\" 污染指数=\"80\" 首要污染物=\"PM2.5\" SO2DATA=\"6.0\" SO2IAQI=\"\" NO2DATA=\"58.0\" NO2IAQI=\"\" PM10DATA=\"93.0\" PM10IAQI=\"\" PM25DATA=\"59.0\" PM25IAQI=\"\" CODATA=\"1.1\" COIAQI=\"\" O31DATA=\"4.0\" O31IAQI=\"\"/><实况 时间=\"2019-10-10 04\" 空气质量状况=\"良\" 空气质量等级=\"二级\" 污染指数=\"82\" 首要污染物=\"PM2.5\" SO2DATA=\"6.0\" SO2IAQI=\"\" NO2DATA=\"55.0\" NO2IAQI=\"\" PM10DATA=\"104.0\" PM10IAQI=\"\" PM25DATA=\"60.0\" PM25IAQI=\"\" CODATA=\"1.1\" COIAQI=\"\" O31DATA=\"4.0\" O31IAQI=\"\"/><实况 时间=\"2019-10-10 05\" 空气质量状况=\"良\" 空气质量等级=\"二级\" 污染指数=\"80\" 首要污染物=\"PM2.5\" SO2DATA=\"7.0\" SO2IAQI=\"\" NO2DATA=\"52.0\" NO2IAQI=\"\" PM10DATA=\"108.0\" PM10IAQI=\"\" PM25DATA=\"59.0\" PM25IAQI=\"\" CODATA=\"1.2\" COIAQI=\"\" O31DATA=\"4.0\" O31IAQI=\"\"/><实况 时间=\"2019-10-10 06\" 空气质量状况=\"良\" 空气质量等级=\"二级\" 污染指数=\"80\" 首要污染物=\"PM2.5\" SO2DATA=\"7.0\" SO2IAQI=\"\" NO2DATA=\"48.0\" NO2IAQI=\"\" PM10DATA=\"104.0\" PM10IAQI=\"\" PM25DATA=\"59.0\" PM25IAQI=\"\" CODATA=\"1.2\" COIAQI=\"\" O31DATA=\"6.0\" O31IAQI=\"\"/><实况 时间=\"2019-10-10 07\" 空气质量状况=\"良\" 空气质量等级=\"二级\" 污染指数=\"87\" 首要污染物=\"PM2.5\" SO2DATA=\"7.0\" SO2IAQI=\"\" NO2DATA=\"51.0\" NO2IAQI=\"\" PM10DATA=\"107.0\" PM10IAQI=\"\" PM25DATA=\"64.0\" PM25IAQI=\"\" CODATA=\"1.2\" COIAQI=\"\" O31DATA=\"13.0\" O31IAQI=\"\"/><实况 时间=\"2019-10-10 08\" 空气质量状况=\"良\" 空气质量等级=\"二级\" 污染指数=\"90\" 首要污染物=\"PM2.5\" SO2DATA=\"8.0\" SO2IAQI=\"\" NO2DATA=\"56.0\" NO2IAQI=\"\" PM10DATA=\"106.0\" PM10IAQI=\"\" PM25DATA=\"67.0\" PM25IAQI=\"\" CODATA=\"1.3\" COIAQI=\"\" O31DATA=\"28.0\" O31IAQI=\"\"/></城市></地区></省></数据>";
        JSONObject jsonObject = xmltoJson(xml);
        System.out.println(jsonObject);
    }
}
