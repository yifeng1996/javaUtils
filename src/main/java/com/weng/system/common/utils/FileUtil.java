package com.weng.system.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class FileUtil {

    /**
     * @Description: 文件转成二进制字符串
     * @param file
     * @Author: weng.yifeng
     * @Date: 2022/8/19 17:05
     **/
    public static String fileToBinStr(File file) {
        try {
            InputStream fis = new FileInputStream(file);
            byte[] bytes = FileCopyUtils.copyToByteArray(fis);
            return new String(bytes, "ISO-8859-1");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return "";
    }

    /**
     * @Description: 二进制字符串转成文件
     * @param binStr 二进制字符串
     * @param parent    生成文件的路径
     * @param fileName  生成的文件名称（带后缀）
     * @Author: weng.yifeng
     * @Date: 2022/8/19 17:05
     **/
    public static String binStrToFile(String binStr, String parent, String fileName) {
        if (StringUtils.isBlank(binStr) || StringUtils.isBlank(parent) || StringUtils.isBlank(fileName)) {
            return "";
        }
        try {
            File file = new File(parent, fileName);
            file.createNewFile();
            byte[] bytes = binStr.getBytes("ISO-8859-1");
            FileCopyUtils.copy(bytes,file);
            return parent + "/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return "";
    }

    public static void main(String[] args){
        File file = new File("E:/TRS/TRS数据迁移/省疾控应用部署和数据迁移/jk/2020052506.jpg");
        String filePath = binStrToFile(fileToBinStr(file), "E:/TRS/TRS数据迁移/宁波公安厅/附件", "附件ces.jpg");
        System.out.println(filePath);
    }
}
