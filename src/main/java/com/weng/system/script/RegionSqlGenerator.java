package com.weng.system.script;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * 从 regionData.properties 批量生成地域 SQL 的独立脚本。
 */
public class RegionSqlGenerator {

    private static String sql = "INSERT INTO `wznetdigitalmanage`.`region`(`region_id`, `name`, `parent_id`, `order_num`, `deleted`, `region_code`) VALUES (%s, '%s', %s, NULL, 0, '%s');";

    public static void main(String[] args) {
        // 读取的地域编码
        File file = new File("regionData.properties");
        BufferedReader br = null;
        // 生成的SQL文件地址
        File generateSqlFile = new File("generateSql.properties");
        BufferedWriter bw = null;
        try {
            if (file.exists()) {
                br = new BufferedReader(new FileReader(file));
                bw = new BufferedWriter(new FileWriter(generateSqlFile));
                String line = "";
                while ((line = br.readLine()) != null && line.split(":").length == 2) {
                    String regionCode = line.split(":")[0];
                    String regionName = line.split(":")[1];
                    if (!StringUtils.isEmpty(regionCode) && !StringUtils.isEmpty(regionName)) {
                        // 拼接、生成SQL语句
                        String regionId = regionCode.substring(0, 9);
                        String parentId = regionCode.substring(0, 6);
                        String generateSql = String.format(sql, regionId, regionName, parentId, regionCode);
                        bw.write(generateSql + "\n");
                        bw.flush();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null){
                    br.close();
                }
                if (bw != null){
                    bw.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
