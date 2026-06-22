package com.weng.system.common.utils;

import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * XML 数据转 JSON 工具类
 *
 * <p>转换规则：
 * <ul>
 *   <li>元素的属性以 {@code @} 前缀映射，如 {@code <name id="1"/>} → {@code {"@id":"1"}}</li>
 *   <li>元素的文本内容以 {@code #text} 为键映射，如 {@code <name>张三</name>} → {@code {"#text":"张三"}}</li>
 *   <li>同名同级元素自动合并为 JSONArray</li>
 * </ul>
 *
 * @author yifeng
 * @since 2019/9/23
 * @version 3.0
 */
public class XmlToJsonUtil {

    private XmlToJsonUtil() {
        // 工具类禁止实例化
    }

    /**
     * XML 字符串转为 JSONObject
     *
     * @param xml XML 字符串，不能为 null 或空
     * @return JSONObject
     * @throws IllegalArgumentException 当 xml 为 null/空，或解析失败时抛出
     */
    public static JSONObject xmltoJson(String xml) {
        if (xml == null || xml.trim().isEmpty()) {
            throw new IllegalArgumentException("XML 内容不能为 null 或空");
        }
        try {
            Document document = DocumentHelper.parseText(xml);
            Element root = document.getRootElement();
            JSONObject result = new JSONObject();
            result.put(root.getName(), buildJsonFromElement(root));
            return result;
        } catch (DocumentException e) {
            throw new IllegalArgumentException("XML 解析失败: " + e.getMessage(), e);
        }
    }

    /**
     * 从单个元素构建 JSONObject
     */
    private static JSONObject buildJsonFromElement(Element node) {
        JSONObject json = new JSONObject();
        // 1. 放入属性（@ 前缀）
        putAttributes(node, json);
        // 2. 放入子元素
        List<Element> children = node.elements();
        for (Element child : children) {
            putChildNode(json, child);
        }
        // 3. 放入文本内容（#text）
        String text = node.getTextTrim();
        if (!text.isEmpty()) {
            json.put("#text", text);
        }
        return json;
    }

    /**
     * 将子元素放入父 JSON 中，同名元素自动合并为 JSONArray
     */
    private static void putChildNode(JSONObject parent, Element child) {
        String name = child.getName();
        JSONObject childJson = buildJsonFromElement(child);
        Object existing = parent.get(name);
        if (existing == null) {
            parent.put(name, childJson);
        } else if (existing instanceof JSONArray) {
            ((JSONArray) existing).add(childJson);
        } else {
            JSONArray array = new JSONArray();
            array.add(existing);
            array.add(childJson);
            parent.put(name, array);
        }
    }

    /**
     * 将元素的所有属性放入 JSON，键名加 {@code @} 前缀
     */
    private static void putAttributes(Element node, JSONObject json) {
        for (Attribute attr : node.attributes()) {
            json.put("@" + attr.getName(), attr.getValue());
        }
    }
}
