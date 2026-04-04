package com.weng.system.common.utils.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author YangKai
 * @version 1.0.0
 * @ClassName EasyExcelUtil
 * @Description TODO
 * @createTime 2021/3/31 16:02
 * @project propagateAnalysev2
 */
public class EasyExcelUtil {

    private EasyExcelUtil() {

    }

    /**
     * 导出数据
     *
     * @param list      导出的数据
     * @param fileName  导出的文件名
     * @param response  响应
     * @param className exportVO的类对象
     * @param sheetName sheet名字
     */
    public static void exportList(List<?> list, String fileName, HttpServletResponse response, Class<?> className,
                                  String sheetName) throws Exception {
        if (CollectionUtils.isNotEmpty(list)) {
            boolean flag = list.get(0).getClass() == className;
            if (!flag) {
                throw new RuntimeException("导出数据与实体不一致");
            }
        }
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), className)
                .sheet(sheetName).doWrite(list);

    }
    /**
     * 导出数据（导出CSV）
     *
     * @param list      导出的数据
     * @param fileName  导出的文件名
     * @param response  响应
     * @param className exportVO的类对象
     * @param sheetName sheet名字
     */
    public static void exportListCSV(List<?> list, String fileName, HttpServletResponse response, Class<?> className,
                                     String sheetName) throws Exception {
        if (CollectionUtils.isNotEmpty(list)) {
            boolean flag = list.get(0).getClass() == className;
            if (!flag) {
                throw new RuntimeException("导出数据与实体不一致");
            }
        }
        response.setContentType("text/csv");
        response.setCharacterEncoding("utf-8");
        fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".csv");
        EasyExcel.write(response.getOutputStream(), className).excelType(ExcelTypeEnum.CSV).charset(Charset.forName("GBK"))
                .sheet(sheetName).doWrite(list);

    }

    /**
     * 读 文件流
     *
     * @param inputStream inputStream-文件流
     * @param clazz       clazz-excel内sheet对应的对象
     * @param sheetNo     sheetNo-sheet下标（从0开始）
     * @param headRowNum  headRowNum-sheet有效数据行数（从0开始
     * @param <T>         return
     * @return
     */
    public static <T> List<T> syncReadModel(InputStream inputStream, Class<T> clazz, Integer sheetNo,
                                            Integer headRowNum) {
        return EasyExcelFactory.read(inputStream).sheet(sheetNo).headRowNumber(headRowNum).head(clazz).doReadSync();
    }

    /**
     * 读 文件
     *
     * @param file       文件
     * @param clazz      clazz-excel内sheet对应的对象
     * @param sheetNo    sheetNo-sheet下标（从0开始）
     * @param headRowNum headRowNum-sheet有效数据行数（从0开始
     * @param <T>        return
     * @return
     */
    public static <T> List<T> syncReadModel(File file, Class clazz, Integer sheetNo, Integer headRowNum) {
        return EasyExcelFactory.read(file).sheet(sheetNo).headRowNumber(headRowNum).head(clazz).doReadSync();
    }

    /**
     * 切割list
     *
     * @param list
     * @param fromScope
     * @param toScope
     * @param <T>
     * @return
     */
    public static <T> List<T> subList(List<T> list, Integer fromScope, Integer toScope) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        if (fromScope != null && toScope != null) {
            if (toScope < fromScope) {
                throw new RuntimeException("截取结束位置大于开始位置,请输入正确的开始和结束位置!");
            }
            if (list.size() < fromScope || list.size() < toScope) {
                throw new RuntimeException("截取位置超过最大长度!");
            }
            list = list.subList(fromScope - 1, toScope);
        }
        return list;
    }

    /**
     * 多个不同的对象导出不同的sheet
     * @param lists
     * @param fileName
     * @param sheetName
     * @param response
     * @param className
     * @throws Exception
     */
    public static void exportList(List<List> lists, String fileName, List<String> sheetName, HttpServletResponse response,
                                  List<Class> className) throws Exception {
        boolean flag = true;
        if (CollectionUtils.isNotEmpty(lists) && CollectionUtils.isNotEmpty(className) && lists.size() == className.size()) {
            for (int i = 0; i < lists.size(); i++) {
                if (CollectionUtils.isNotEmpty(lists.get(i)) && className.get(i) != null) {
                    flag = lists.get(i).get(0).getClass() == className.get(i);
                    if (!flag) {
                        throw new RuntimeException("导出数据与实体不一致");
                    }
                }
            }
        }
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        fileName = URLEncoder.encode(fileName, "utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
        try{
            //新建ExcelWriter
            for (int i = 0; i < lists.size(); i++) {
                WriteSheet sheet = EasyExcel.writerSheet(i, sheetName.get(i)).head(className.get(i)).build();
                excelWriter.write(lists.get(i), sheet);
            }
        }finally {
            //关闭流
            excelWriter.finish();
        }
    }

    /**
     * 导出自定义表头数据
     *
     * @param heads     导出的表头列表
     * @param fileName  导出的文件名
     * @param response  响应
     * @param sheetName sheet名字
     * @param list      导出的数据list
     */
    public static void exportHeader(List<List<String>> heads, String fileName, HttpServletResponse response,
                                    String sheetName, List<List<String>> list) throws Exception {
        if (CollectionUtils.isEmpty(heads)) {
            throw new RuntimeException("导出的表头数据为空");
        }
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        fileName = URLEncoder.encode(fileName, "utf-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream()).head(heads)
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .registerWriteHandler(new RowWriteHandlerImpl())
                .sheet(sheetName).doWrite(list);
    }

    /**
     * 根据动态字段列表生成表头
     *
     * @param fieldList 字段名列表
     * @return 表头
     */
    public static List<List<String>> getDynamicHead(List<String> fieldList) {
        List<List<String>> head = new ArrayList<>();
        for (String field : fieldList) {
            List<String> headColumn = new ArrayList<>();
            headColumn.add(field);  // 每一列的名称
            head.add(headColumn);
        }
        return head;
    }
}
