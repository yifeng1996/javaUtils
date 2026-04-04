package com.weng.system.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weng.system.common.constant.QueryRequest;
import com.weng.system.common.utils.excel.EasyExcelUtil;
import com.weng.system.common.utils.excel.RowWriteHandlerImpl;
import com.weng.system.entity.AppConfig;
import com.weng.system.entity.ExportDimensionExcelVO;
import com.weng.system.mapper.AppConfigMapper;
import com.weng.system.service.AppConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 配置表接口实现
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AppConfigServiceImpl extends ServiceImpl<AppConfigMapper, AppConfig> implements AppConfigService {

    @Autowired
    private AppConfigMapper appConfigMapper;

    @Override
    public List<AppConfig> list(AppConfig appConfig) {
        LambdaQueryWrapper<AppConfig> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件 参考https://mp.baomidou.com/guide/wrapper.html#abstractwrapper
        if (StringUtils.isNotBlank(appConfig.getConfigName())){
            queryWrapper.like(AppConfig::getConfigName, appConfig.getConfigName());
        }
        return appConfigMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<AppConfig> listPage(QueryRequest request, AppConfig appConfig) {
        LambdaQueryWrapper<AppConfig> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<AppConfig> page = new Page<>(request.getPageNum(), request.getPageSize());
        if (StringUtils.isNotBlank(appConfig.getConfigName())){
            queryWrapper.like(AppConfig::getConfigName, appConfig.getConfigName());
        }
        //需要排序参考
        //com.trs.job.service.impl.JobServiceImpl findJob()方法
        //SortUtil.handlePageSort(request, page, "orderFile", TRSConstant.ORDER_DESC, true);

        return page(page, queryWrapper);
    }

    @Override
    public void exportExcel(HttpServletResponse response) throws Exception {
        List<ExportDimensionExcelVO> list = new ArrayList<>();
        list.add(new ExportDimensionExcelVO(1,"微博", "人民号", "微博栏目", "人民日报"));
        list.add(new ExportDimensionExcelVO(1,"微博", "微博测试111", "微博栏目", "测试111"));
        list.add(new ExportDimensionExcelVO(1,"微博", "微博测试222", "微博栏目", "测试222"));
        list.add(new ExportDimensionExcelVO(1,"微博", "微博测试333", "微博栏目", "测试333"));
        list.add(new ExportDimensionExcelVO(1,"微信", "微信测试222", "微信栏目", "微信测试222"));
        list.add(new ExportDimensionExcelVO(1,"微信", "微信测试333", "微信栏目", "微信测试333"));
        list.add(new ExportDimensionExcelVO(1,"网站", "网站测试222", "网站栏目", "网站测试222"));
        list.add(new ExportDimensionExcelVO(1,"网站", "网站测试333", "网站栏目", "网站测试333"));
        List<String> situationList = new ArrayList<>(Arrays.asList("微博", "微信", "网站"));
        String fileName = "导出";
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();


        for (String situation : situationList) {
            if ("微博".equals(situation)){
                List<String> fieldList = new ArrayList<>(Arrays.asList("number", "situation", "msiteName", "mboardName"));
                List<String> fieldNameList = new ArrayList<>(Arrays.asList("序号", "舆论场", "站点", "栏目"));
                List<ExportDimensionExcelVO> collect = list.stream().filter(a -> situation.equals(a.getSituation())).collect(Collectors.toList());
                List<List<Object>> dynamicData = getDynamicData(collect, fieldList);
                WriteSheet build = EasyExcel.writerSheet(situation).head(EasyExcelUtil.getDynamicHead(fieldNameList))
                        .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                        .registerWriteHandler(new RowWriteHandlerImpl()).build();
                excelWriter.write(dynamicData, build);
            }else {
                List<String> fieldList = new ArrayList<>(Arrays.asList("number", "situation", "msiteName", "mboardName", "uname"));
                List<String> fieldNameList = new ArrayList<>(Arrays.asList("序号", "舆论场", "站点", "栏目", "账号"));
                List<ExportDimensionExcelVO> collect = list.stream().filter(a -> situation.equals(a.getSituation())).collect(Collectors.toList());
                List<List<Object>> dynamicData = getDynamicData(collect, fieldList);
                WriteSheet build = EasyExcel.writerSheet(situation).head(EasyExcelUtil.getDynamicHead(fieldNameList))
                        .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                        .registerWriteHandler(new RowWriteHandlerImpl()).build();
                excelWriter.write(dynamicData, build);
            }
        }
        excelWriter.finish();
    }
    /**
     * 根据字段列表和数据动态生成需要导出的数据
     *
     * @param data      数据列表
     * @param fieldList 字段名称列表
     * @return 导出的数据
     */
    private static List<List<Object>> getDynamicData(List<ExportDimensionExcelVO> data, List<String> fieldList) {
        List<List<Object>> rows = new ArrayList<>();
        for (ExportDimensionExcelVO excelVO : data) {
            List<Object> row = new ArrayList<>();
            for (String fieldName : fieldList) {
                try {
                    // 通过反射获取字段值
                    Field field = excelVO.getClass().getDeclaredField(fieldName);
                    // 设置可访问
                    field.setAccessible(true);
                    Object value = field.get(excelVO);
                    row.add(value);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            rows.add(row);
        }
        return rows;
    }
}
