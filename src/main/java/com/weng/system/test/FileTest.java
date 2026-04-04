package com.weng.system.test;

import java.io.*;

/**
 * @ Description : java类作用描述
 * @ Author : yifeng
 * @ Date : 2019/10/15 14:33
 * @ Version : 1.0
 */
public class FileTest {
    public static void main(String[] args){
        /*File file = new File("G:/src/W020191128623270526770.doc");
        System.out.println(file.exists());
        System.out.println(file.isFile());
        System.out.println(file.isDirectory());*/


        StringBuffer sb = new StringBuffer();
        FileInputStream fis = null;
        BufferedReader br = null;
        try {
            File f = new File("E:/TRS/TRS数据迁移/宁波公安厅/001/2002-01-04/1953989.htm");
            fis = new FileInputStream(f);
            br = new BufferedReader(new InputStreamReader(fis));
            String s = "";
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
        } catch (Exception e) {

        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                }
            }
        }
        System.out.println(sb);
    }
}
