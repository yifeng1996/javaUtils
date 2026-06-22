package com.weng.system.lab;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @ Description : java类作用描述
 * @ Author : yifeng
 * @ Date : 2019/10/15 14:33
 * @ Version : 1.0
 */
public class StringTest1 {

    public static void main(String[] args){

        /*String string = "[{\"fileName\":\"20191129153641441.pdf\",\"fileExt\":\"pdf\"}]";
        JSONArray jsonArray = JSONObject.parseArray(string);
        int len = jsonArray.size();
        for (int i=0;i<len;i++){
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            String aaa = jsonObject.getString("aaa");
            //重新设置newJsonObject
            JSONObject newJsonObject = new JSONObject();
            newJsonObject.put("fileName","20191129153641441.pdf");
            newJsonObject.put("fileExt","pdf");
            jsonArray.add(newJsonObject);
        }
        System.out.println(jsonArray.toString());*/

        /*if (fileType.equals("")){
            System.out.println("空的值");
        }*/
        /*String string = "资金及结余情况和第7页（二）部分区县城市低保资金未实行专户管理、专\\t账核算问题，作以下  说明，建议作适当修改：\\r\\n 1.对于绍兴市2008年和2009年财政预��e��e";
        string = string.replaceAll("\\s*","").replaceAll("\\\\r","").replaceAll("\\\\n","").replaceAll("\\\\t","").replaceAll("��e","");
        String string1 = "资金部分及结余情况和第7页（二）部分区县城市低保资金未实行专户管理";
        string1 = string1.replaceAll("部分", "11111部分");
        String string2 = "0012300";
        string2 = string2.replaceFirst("^0*", "");*/
        /*String image = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAtMAAAEJCAYAAABWuavlAAAPVklEQVR4Xu3WsQ0AIAwEMbL/0EFiA652+jTWFze7u8cRIECAAAECBAgQIPAtMGL628wDAQIECBAgQIAAgScgpg2BAAECBAgQIECAQBQQ0xHOGwECBAgQIECAAAExbQMECBAgQIAAAQIEooCYjnDeCBAgQIAAAQIECIhpGyBAgAABAgQIECAQBcR0hPNGgAABAgQIECBAQEzbAAECBAgQIECAAIEoIKYjnDcCBAgQIECAAAECYtoGCBAgQIAAAQIECEQBMR3hvBEgQIAAAQIECBAQ0zZAgAABAgQIECBAIAqI6QjnjQABAgQIECBAgICYtgECBAgQIECAAAECUUBMRzhvBAgQIECAAAECBMS0DRAgQIAAAQIECBCIAmI6wnkjQIAAAQIECBAgIKZtgAABAgQIECBAgEAUENMRzhsBAgQIECBAgAABMW0DBAgQIECAAAECBKKAmI5w3ggQIECAAAECBAiIaRsgQIAAAQIECBAgEAXEdITzRoAAAQIECBAgQEBM2wABAgQIECBAgACBKCCmI5w3AgQIECBAgAABAmLaBggQIECAAAECBAhEATEd4bwRIECAAAECBAgQENM2QIAAAQIECBAgQCAKiOkI540AAQIECBAgQICAmLYBAgQIECBAgAABAlFATEc4bwQIECBAgAABAgTEtA0QIECAAAECBAgQiAJiOsJ5I0CAAAECBAgQICCmbYAAAQIECBAgQIBAFBDTEc4bAQIECBAgQIAAATFtAwQIECBAgAABAgSigJiOcN4IECBAgAABAgQIiGkbIECAAAECBAgQIBAFxHSE80aAAAECBAgQIEBATNsAAQIECBAgQIAAgSggpiOcNwIECBAgQIAAAQJi2gYIECBAgAABAgQIRAExHeG8ESBAgAABAgQIEBDTNkCAAAECBAgQIEAgCojpCOeNAAECBAgQIECAgJi2AQ";
        System.out.println(StringUtils.indexOf(image, "base64,"));
        image = StringUtils.substring(image, 0, StringUtils.indexOf(image,"base64,"));*/
//        StringBuilder ocrContent = new StringBuilder();
//        ocrContent.append("测试1").append("#####").append("测试2").append("#####").append("测试3").append("#");
//        ocrContent.delete(ocrContent.length() - 5, ocrContent.length());
//        ocrContent.deleteCharAt(ocrContent.length()-1);
//        publicOpinionRisk = StringUtils.replace(publicOpinionRisk, "\n", "");
        /*String sourceName = "a_ces_";
        int firstUnderscoreIndex = sourceName.indexOf(StringPool.UNDERSCORE);
        System.out.println(sourceName.substring(0, firstUnderscoreIndex));
        System.out.println(sourceName.substring(firstUnderscoreIndex + 1));

        Long l = null;
        long ll = l;*/
        /*String sourceName = "a_ces_";
        String sourceName1 = null;
        sourceName = sourceName1 + "_" + sourceName;
        System.out.printf("long值：" + sourceName);*/

        /*String result = "转发相关性：0分\n" +
                "账号名风险：有风险";
        System.out.println(result.contains("\n"));
        String zjAiIrrelevantForward = "";
        if (result.contains("转发相关性：")){
            zjAiIrrelevantForward = result.split("\\n")[0].split("：")[1];
        }
        String zjAiHighRisk = "";
        if (result.contains("账号名风险：")){
            zjAiHighRisk = result.split("\\n")[1].split("：")[1];
        }
        System.out.println(zjAiIrrelevantForward);
        System.out.println(zjAiHighRisk);*/
        String result = "转发相关性";
        String class1 = StringUtils.substringBeforeLast(result, StringPool.UNDERSCORE);
        String class2 = StringUtils.substringAfterLast(result, StringPool.UNDERSCORE);
        System.out.println(class1);
        System.out.println(class2);
    }
}
