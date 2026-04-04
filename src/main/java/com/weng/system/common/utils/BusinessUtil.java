package com.weng.system.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.util.Utf8;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class BusinessUtil {

    private static Pattern pattern_change = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
    private static final String regEx_space = "\\s*|\t|\r|\n";// 定义空格回车换行符
    private static final String regEx_w = "<w[^>]*?>[\\s\\S]*?<\\/w[^>]*?>";//定义所有w标签
    private static final String REGEX_SPECIAL_CHARACTER = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";


    public static final ImmutableSet<Class> BASE_TYPE = ImmutableSet.of(
            Integer.class,
            Long.class,
            Double.class,
            Float.class,
            String.class,
            Date.class
    );

    /**
     * 把map转成指定类型的JavaBean对象
     *
     * @param map
     * @param clazz
     * @return
     */
    public static <T> T toBean(Map<String, Object> map,  Class<T> clazz) {
        //map下划线转驼峰
        Map<String, Object> mapNew = new HashMap<>(200);
        for (String key : map.keySet()) {
            mapNew.put(strChange(strChangeBus(key)), map.get(key));
        }
        map = mapNew;
        //bean转化
        T bean = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        processForMap(map,clazz);
        JSONObject jsonObject = new JSONObject(map);
        convertForDeserialization(jsonObject);
        String s = jsonObject.toJSONString();
        try {
            bean = mapper.readValue(s, clazz);
        } catch (Exception e) {
            log.error("AI消息反序列化异常异常 ,Message : {}", "", e);
        }
        return bean;
    }

    /**
     * avro UTF8 => String
     * 并通过反射获取实体字段类型，将基础类型以外的字段尝试json parse。
     *
     * @param map
     */
    public static void processForMap(Map<String, Object> map, Class clazz) {
        Set<String> set = new HashSet<>();
        for (Map.Entry<String, Object> mapInfo : map.entrySet()) {
            String key = mapInfo.getKey();
            Object value = mapInfo.getValue();
            if (Objects.isNull(mapInfo.getValue())) {
                //map.put(mapInfo.getKey(), "");
                set.add(key);
            } else if (value instanceof Utf8) {
                Utf8 utf8 = (Utf8) value;
                String s = utf8.toString();
                try {
                    Field field = clazz.getDeclaredField(key);
                    if (BASE_TYPE.contains(field.getType())) {
                        // 基本类型不需要 json parse
                        mapInfo.setValue(s);
                    } else {
                        try {
                            // 尝试解析
                            mapInfo.setValue(JSONObject.parse(s));
                        } catch (Exception e) {
                            mapInfo.setValue(s);
                        }
                    }
                } catch (Exception e) {
                    // 保证 arvo utf8 转换为 string
                    mapInfo.setValue(s);
                }
            }
        }
        for (String key : set) {
            map.remove(key);
        }
    }

    private static String strChangeBus(String str){
        if(StringUtils.isNotBlank(str)&&str.length()>2&&str.substring(1,2).equals("_")){
            str = str.substring(0,1)+str.substring(2,str.length());
        }
        return str;
    }

    /**
     * 下划线转驼峰
     *
     * @param str
     * @return
     */
    public static String strChange(String str) {
        //驼峰和下划线同时存在于map中，无需转换小写
        //str = str.toLowerCase();
        final StringBuffer sb = new StringBuffer();

        Matcher m = pattern_change.matcher(str);
        while (m.find()) {
            m.appendReplacement(sb, m.group(1).toUpperCase());
        }
        m.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线
     *
     * @param str
     * @return
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static List<Date> findDates(Date dStart, Date dEnd, int calendar) {
        Calendar cStart = Calendar.getInstance();
        cStart.setTime(dStart);

        List<Date> dateList = new ArrayList();
        // 别忘了，把起始日期加上
        dateList.add(dStart);
        // 此日期是否在指定日期之后
        while (dEnd.after(cStart.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量 Calendar.HOUR
            cStart.add(calendar, 1);
            dateList.add(cStart.getTime());
        }
        if (!CollectionUtils.isEmpty(dateList)) {
            dateList = dateList.subList(0, dateList.size() - 1);
        }
        return dateList;
    }


    /**
     * 获取时间前后2天的数据
     *
     * @param dateTime
     * @return
     */
    public static Map<String, String> getDayScope(String dateTime) {
        Map<String, String> map = new HashMap<>(3);
        map.put("stTime", DateUtil.format(DateUtil.dayAddNum(DateUtil.parse(dateTime, DateUtil.DATE_TIME), -1),
                DateUtil.DATE_TIME));
        map.put("edTime", DateUtil.format(DateUtil.dayAddNum(DateUtil.parse(dateTime, DateUtil.DATE_TIME), 1),
                DateUtil.DATE_TIME));
        return map;
    }

    /**
     * @param htmlStr
     * @return 删除Html标签
     * @author LongJin
     */
    public static String delHTMLTag(String htmlStr) {
        if (StringUtils.isBlank(htmlStr)) {
            return htmlStr;
        }
        htmlStr = StringEscapeUtils.unescapeHtml3(htmlStr);
        // 过滤html标签
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll("");
        if (!m_html.find()) {
            return htmlStr;
        }
        // 过滤script标签
        Pattern p_w = Pattern.compile(regEx_w, Pattern.CASE_INSENSITIVE);
        Matcher m_w = p_w.matcher(htmlStr);
        htmlStr = m_w.replaceAll("");
        // 过滤script标签
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll("");
        // 过滤style标签
        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll("");
        // 过滤空格回车标签
        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
        Matcher m_space = p_space.matcher(htmlStr);
        htmlStr = m_space.replaceAll("");
        //过滤空白字符串
        htmlStr = htmlStr.replaceAll(" ", "");
        return htmlStr.trim();
    }

    /**
     * 实体反序列化前 做一些转换
     * 1、处理空值
     * 2、处理默认值
     */
    public static void convertForDeserialization(JSONObject jsonObject) {
        //emptyStringToNull(jsonObject, "m_video_files");
        emptyStringToNull(jsonObject, "nmedia");
        emptyStringToNull(jsonObject, "ncontentPlace");
        emptyStringToNull(jsonObject, "ntitlePlace");
        emptyStringToNull(jsonObject, "ncontentPerson");
        emptyStringToNull(jsonObject, "ntitlePerson");
        emptyStringToNull(jsonObject, "ncontentOrg");
        emptyStringToNull(jsonObject, "ntitleOrg");
        emptyStringToNull(jsonObject, "ncontentSpam");
        emptyStringToNull(jsonObject, "yyqWarnClass");
        emptyStringToNull(jsonObject, "yyqFocus");
        emptyStringToNull(jsonObject, "avideoKeyFrame");
        emptyStringToNull(jsonObject, "avideoFaceregContents");
        emptyStringToNull(jsonObject, "ycbMedia");
        emptyStringToNull(jsonObject, "ycbMediaAccount");
        emptyStringToNull(jsonObject, "htextIdentificationArray");
        emptyStringToNull(jsonObject, "yyqWarnKeywordClass");
        emptyStringToNull(jsonObject, "mpublishTime");
        emptyStringToNull(jsonObject, "mgatherTime");
        emptyStringToNull(jsonObject, "minsertTime");
        emptyStringToNull(jsonObject, "mupdateTime");
        emptyStringToNull(jsonObject, "htextIdentificationTime");
        emptyStringToNull(jsonObject, "hpicIdentificationTime");
        emptyStringToNull(jsonObject, "haudioIdentificationTime");
        emptyStringToNull(jsonObject, "hvideoIdentificationTime");
        emptyStringToNull(jsonObject, "yharmConfirmTime");
        emptyStringToNull(jsonObject, "yyqWarnTime");
        emptyStringToNull(jsonObject, "ucreatedTime");

        emptyStringToDefaultValue(jsonObject, "mforwardCnt", 0);
        emptyStringToDefaultValue(jsonObject, "mreplyCnt", 0);
        emptyStringToDefaultValue(jsonObject, "mlikeCnt", 0);
        emptyStringToDefaultValue(jsonObject, "mreadCnt", 0);
        emptyStringToDefaultValue(jsonObject, "mreadingCnt", 0);
        emptyStringToDefaultValue(jsonObject, "mtrampledCnt", 0);
        emptyStringToDefaultValue(jsonObject, "ncontentCount", 0);
        emptyStringToDefaultValue(jsonObject, "ysimilarCount", 0);
        emptyStringToDefaultValue(jsonObject, "uweiboCount", 0);
        emptyStringToDefaultValue(jsonObject, "ufriendsCount", 0);
        emptyStringToDefaultValue(jsonObject, "ufansCount", 0);
    }

    private static void emptyStringToDefaultValue(JSONObject jsonObject, String key, Object defaultValue) {
        String value = jsonObject.getString(key);
        if (StringUtils.isEmpty(value)) {
            jsonObject.put(key, defaultValue);
        }
    }

    private static void emptyStringToNull(JSONObject jsonObject, String key) {
        String value = jsonObject.getString(key);
        if (StringUtils.isBlank(value)) {
            jsonObject.remove(key);
        }
    }

    /**
     * 判断是否有特殊字符
     *
     * @param keyword 参数
     * @return true：包含；false：不包含
     */
    public static boolean isSpecialCharacter(String keyword) {
        Pattern p = Pattern.compile(REGEX_SPECIAL_CHARACTER);
        Matcher m = p.matcher(keyword);
        return m.find();
    }

}
