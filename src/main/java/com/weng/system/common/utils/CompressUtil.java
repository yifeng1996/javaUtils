package com.weng.system.common.utils;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CompressUtil {

    private static CompressUtil compressUtil;
    @PostConstruct
    public void init() {
        compressUtil = this;
    }

    /**
     * @Description: 处理附件名称相同的情况，附件名称相同的在后面加序号
     * @param fileNameList
     * @Author: weng.yifeng
     * @Date: 2021/6/28 9:25
     **/
    private static void handleFileName(List<String> fileNameList) {
        List<String> repeatList = fileNameList.stream().collect(Collectors.toMap(e -> e, e -> 1, (a, b) -> a + b)) // 获得元素出现频率的 Map，键为元素，值为元素出现的次数
                .entrySet().stream()    // Set<Entry>转换为Stream<Entry>
                .filter(entry -> entry.getValue() > 1)  // 过滤出元素出现次数大于 1 的 entry
                .map(Map.Entry::getKey) // 获得 entry 的键（重复元素）对应的 Stream
                .collect(Collectors.toList());  // 转化为 List
        for (String repeat : repeatList){
            int i = 1;
            for (int j = 0; j < fileNameList.size(); j++) {
                String fileName = fileNameList.get(j);
                if (repeat.equals(fileName)){
                    fileName = StringUtils.substring(fileName, 0, StringUtils.lastIndexOf(fileName, StringPool.DOT))
                            + i
                            + StringPool.DOT
                            + StringUtils.substringAfterLast(fileName, StringPool.DOT);
                    fileNameList.set(j, fileName);
                    i++;
                }
            }
        }
    }

    public static void main(String[] arg){
        List<String> list = new ArrayList<>();
        list.add("测试文件.docx");
        list.add("测试excel.xlsx");
        list.add("测试文件.docx");
        list.add("测试文件.docx");
        handleFileName(list);
    }
}
