package com.weng.system.controller;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * @ClassName MavenJarController
 * @Description: TODO
 * @Author weng.yifeng
 * @Date 2022/8/8 16:56
 * @Version V1.0
 **/
public class MavenJarController {

    private static String pomDependency = "<dependency>\n" +
            "\t<groupId>%s</groupId>\n" +
            "\t<artifactId>%s</artifactId>\n" +
            "\t<version>%s</version>\n" +
            "\t<scope>%s</scope>\n" +
            "\t<systemPath>%s</systemPath>\n" +
            "</dependency>";


    public static void main(String[] args) {
        // 读取jar包路径
        File file = new File("E:/TrsIdeaProject/trswcmdeveloper/src/main/resources/lib");
        // 生成的pom文件地址
        File pomFile = new File("pom.properties");
        BufferedWriter bw = null;
        try {
            if (file.exists()) {
                bw = new BufferedWriter(new FileWriter(pomFile));
                File[] files = file.listFiles();
                for (File jarFile : files) {
                    String jarName = jarFile.getName();
                    if (!StringUtils.isEmpty(jarName)) {
                        // 拼接、生成SQL语句
                        String groupId = "trswcmdeveloper." + jarName.substring(0, jarName.indexOf("-"));
                        String artifactId = jarName.substring(0, jarName.lastIndexOf("."));
                        String version = "1.0.0";
                        String scope = "system";
                        String systemPath = "${basedir}/src/main/resources/lib/" + jarName;
                        String pom = String.format(pomDependency, groupId, artifactId, version, scope, systemPath);
                        bw.write(pom + "\n");
                        bw.flush();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null){
                    bw.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
