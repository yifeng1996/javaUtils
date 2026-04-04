package com.weng.system.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.weng.system.common.utils.XmlToJsonUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  yifeng
 * @ CreateDate    :  2019/9/24 13:30
 * @ Version       :  1.0
 */
@RestController
public class AirQualityController {

    @PostMapping(value = "findAirQuality")
    public static Object findAirQuality(/*HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "code", required = true) String code*/) throws Exception{


        JSONObject jsonObject = xmlChange();
        JSONObject jsonObject1 = (JSONObject)jsonObject.get("数据");
        JSONObject jsonObject2 = (JSONObject)jsonObject1.get("省");
        JSONObject jsonObject3 = (JSONObject)jsonObject2.get("地区");
        JSONObject jsonObject4 = (JSONObject)jsonObject3.get("城市");
        JSONArray jsonArray5 = (JSONArray)jsonObject4.get("实况");
        JSONObject jsonObject5 = jsonArray5.getJSONObject(jsonArray5.size()-1);
        System.out.println(jsonObject5);

        return jsonObject5;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public static JSONObject xmlChange() throws Exception {
        String xml = "<?xml version=\"1.0\"?>\n" +
                "\n" +
                "-<ns:queryAQIReportsResponse xmlns:ns=\"http://webservice.shengzhan.sword.fpi.com\">\n" +
                "\n" +
                "<ns:return><?xml version=\"1.0\" encoding=\"UTF-8\"?> <数据 类型=\"城市AQI实时报\" 开始时间=\"2019-10-10 00\" 结束时间=\"2019-10-10 23\"><省 名称=\"浙江\"><地区 名称=\"杭州\" 编码=\"330100\"><城市 名称=\"杭州市\" 编码=\"330101\"><实况 时间=\"2019-10-10 03\" 空气质量状况=\"良\" 空气质量等级=\"二级\" 污染指数=\"80\" 首要污染物=\"PM2.5\" SO2DATA=\"6.0\" SO2IAQI=\"\" NO2DATA=\"58.0\" NO2IAQI=\"\" PM10DATA=\"93.0\" PM10IAQI=\"\" PM25DATA=\"59.0\" PM25IAQI=\"\" CODATA=\"1.1\" COIAQI=\"\" O31DATA=\"4.0\" O31IAQI=\"\"/><实况 时间=\"2019-10-10 04\" 空气质量状况=\"良\" 空气质量等级=\"二级\" 污染指数=\"82\" 首要污染物=\"PM2.5\" SO2DATA=\"6.0\" SO2IAQI=\"\" NO2DATA=\"55.0\" NO2IAQI=\"\" PM10DATA=\"104.0\" PM10IAQI=\"\" PM25DATA=\"60.0\" PM25IAQI=\"\" CODATA=\"1.1\" COIAQI=\"\" O31DATA=\"4.0\" O31IAQI=\"\"/><实况 时间=\"2019-10-10 05\" 空气质量状况=\"良\" 空气质量等级=\"二级\" 污染指数=\"80\" 首要污染物=\"PM2.5\" SO2DATA=\"7.0\" SO2IAQI=\"\" NO2DATA=\"52.0\" NO2IAQI=\"\" PM10DATA=\"108.0\" PM10IAQI=\"\" PM25DATA=\"59.0\" PM25IAQI=\"\" CODATA=\"1.2\" COIAQI=\"\" O31DATA=\"4.0\" O31IAQI=\"\"/><实况 时间=\"2019-10-10 06\" 空气质量状况=\"良\" 空气质量等级=\"二级\" 污染指数=\"80\" 首要污染物=\"PM2.5\" SO2DATA=\"7.0\" SO2IAQI=\"\" NO2DATA=\"48.0\" NO2IAQI=\"\" PM10DATA=\"104.0\" PM10IAQI=\"\" PM25DATA=\"59.0\" PM25IAQI=\"\" CODATA=\"1.2\" COIAQI=\"\" O31DATA=\"6.0\" O31IAQI=\"\"/><实况 时间=\"2019-10-10 07\" 空气质量状况=\"良\" 空气质量等级=\"二级\" 污染指数=\"87\" 首要污染物=\"PM2.5\" SO2DATA=\"7.0\" SO2IAQI=\"\" NO2DATA=\"51.0\" NO2IAQI=\"\" PM10DATA=\"107.0\" PM10IAQI=\"\" PM25DATA=\"64.0\" PM25IAQI=\"\" CODATA=\"1.2\" COIAQI=\"\" O31DATA=\"13.0\" O31IAQI=\"\"/><实况 时间=\"2019-10-10 08\" 空气质量状况=\"良\" 空气质量等级=\"二级\" 污染指数=\"90\" 首要污染物=\"PM2.5\" SO2DATA=\"8.0\" SO2IAQI=\"\" NO2DATA=\"56.0\" NO2IAQI=\"\" PM10DATA=\"106.0\" PM10IAQI=\"\" PM25DATA=\"67.0\" PM25IAQI=\"\" CODATA=\"1.3\" COIAQI=\"\" O31DATA=\"28.0\" O31IAQI=\"\"/></城市></地区></省></数据></ns:return>\n" +
                "\n" +
                "</ns:queryAQIReportsResponse>";
        String newXml = xml.substring(xml.lastIndexOf("<ns:return>")+11, xml.lastIndexOf("</ns:return>"));
        JSONObject jsonObject = XmlToJsonUtil.xmltoJson(newXml);
        return jsonObject;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public static JSONArray jsonChange() throws Exception {
        String json = "[{\"AREAID\":\"80A1488574FD459DA909333459D004E5\",\"AREANAME\":\"杭州市\",\"OUTPUBLISHSTATE\":1,\"PUBLISHTIME\":\"2015-01-09 10\",\"AQI24\":160,\"AQI24END\":180,\"PRIMARYPOLLUTERS24\":\"PM2.5\",\"VISIBILITY24\":null,\"AQI48\":151,\"AQI48END\":151,\"PRIMARYPOLLUTERS48\":\"PM2.5\",\"VISIBILITY48\":null,\"AQI72\":151,\"AQI72END\":151,\"PRIMARYPOLLUTERS72\":\"PM2.5\",\"VISIBILITY72\":null,\"INDEXCATEGORY24\":\"中度污染\",\"AIRLEVELNAME24\":\"四级\",\"AIRLEVELCOLOR24\":\"#FF0000\",\"INDEXCATEGORY48\":\"中度污染\",\"AIRLEVELNAME48\":\"四级\",\"AIRLEVELCOLOR48\":\"#FF0000\",\"INDEXCATEGORY72\":\"中度污染\",\"AIRLEVELNAME72\":\"四级\",\"AIRLEVELCOLOR72\":\"#FF0000\",\"INDEXCATEGORY24END\":\"中度污染\",\"AIRLEVELNAME24END\":\"四级\",\"AIRLEVELCOLOR24END\":\"#FF0000\",\"INDEXCATEGORY48END\":\"中度污染\",\"AIRLEVELNAME48END\":\"四级\",\"AIRLEVELCOLOR48END\":\"#FF0000\",\"INDEXCATEGORY72END\":\"中度污染\",\"AIRLEVELNAME72END\":\"四级\",\"AIRLEVELCOLOR72END\":\"#FF0000\"}]";
        JSONArray jsonArray = JSONObject.parseArray(json);
        return jsonArray;
    }

    /**
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        findAirQuality();
    }
}
