package com.weng.system.test;

import com.alibaba.fastjson.JSON;
import com.weng.system.entity.DispatchReturn;

/**
 * @ Description : java类作用描述
 * @ Author : yifeng
 * @ Date : 2019/10/15 14:33
 * @ Version : 1.0
 */
public class JsonTest {
    public static void main(String[] args) throws  Exception{
        //设置请求参数的json字符串
        /*Map<String,Object> map = new HashMap<>();
        Map<String,Object> tifMap = new HashMap<>();
        Map<String,Object> psMap = new HashMap<>();
        tifMap.put("fileName","123.tif");
        tifMap.put("fileData","123.tif");
        psMap.put("fileName","123.ps");
        psMap.put("fileData","123.ps");
        map.put("docId","123");
        map.put("tifFile",tifMap);
        map.put("psFile",psMap);
        String json = JSONObject.valueToString(map);
        System.out.println(json);*/
        /*String appFile = "W020200113383634457893.gif,tile";   //图片附件appFile，如：W020200113383634457893.gif
        String[] splits = appFile.split(",");
        for (String split:splits){
            System.out.println(split);
        }*/
        //拼接图片附件相对路径地址
        /*if(!CMyString.isEmpty(appFile)){
            appFile = "/webpic/" + appFile.substring(0, 8) + "/" + appFile.substring(0, 10) + "/" + appFile;
        }*/

        /*String html = Jsoup.parse(f,"gb2312").html();
        System.out.println(html);*/
        String string = "\n" +
                "\n" +
                "{\"MSG\":\"操作成功\",\"DATA\":{\"DOCRELFILE\":[],\"CRTIME\":\"2022-08-18 12:33:21\",\"DOCPUBTIME\":\"\",\"VIDEO\":[],\"CHANNELID\":\"2\",\"DOCSTATUS\":\"1\",\"DOCRELTIME\":\"2018-11-27 00:00:00\",\"AUDIO\":[],\"DOCSOURCENAME\":\"中国疾病预防控制中心\",\"ATTACHPIC\":\"0\",\"WCMMETATABLEGOVDOCNEWSID\":\"53\",\"DOCCONTENT\":\"\",\"DOCID\":\"53\",\"CHNLID\":\"2\",\"DOCTITLE\":\"为啥要检测HIV？\",\"DOCFILE\":[],\"DOCRELVIDEO\":[],\"CHANNELNAME\":\"信息公开_1\",\"THUMBFILES\":\"\",\"DOCTYPE\":\"20\",\"FROMID\":\"53\",\"FROMTYPE\":\"0\",\"DOCRELPIC\":[],\"PICSETS\":[],\"CANEDIT\":\"true\",\"OTHERPUBLISH\":[],\"SUBJECTPIC\":[],\"RECID\":\"53\",\"CHNLIDNAME\":\"信息公开_1\",\"DOCRELNEWS\":[],\"ORIGINMETADATAID\":\"53\",\"SITEID\":\"3\",\"DOCCHANNEL\":\"2\",\"METADATAID\":\"53\",\"ATTACHVIDEO\":\"0\",\"DOCHTMLCON\":\"<DIV class=\\\"TRS_UEDITOR TRS_WEB\\\"><p style=\\\"text-align: center;\\\"><source src=\\\"/ueditor/php/upload/video/20181127/1543298663731798.mp4\\\" type=\\\"video/mp4\\\"/></video></p></DIV>\",\"DOCABSTRACT\":\"\",\"CRUSER\":\"dev\"},\"ISSUCCESS\":\"true\"}";
        DispatchReturn dispatchReturn = JSON.parseObject(string, DispatchReturn.class);
        System.out.println(dispatchReturn);
    }
}
