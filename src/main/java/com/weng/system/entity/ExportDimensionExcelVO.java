package com.weng.system.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.alibaba.excel.enums.BooleanEnum;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import com.alibaba.excel.enums.poi.VerticalAlignmentEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description: 舆情信息导出VO
 * @Author: weng.yifeng
 * @Date: 2023/1/28 14:08
 **/
@Data
@AllArgsConstructor
@ContentRowHeight(30)
// 正文居中
@ContentStyle(wrapped = BooleanEnum.TRUE, verticalAlignment = VerticalAlignmentEnum.CENTER, horizontalAlignment = HorizontalAlignmentEnum.CENTER)
// 头居中
@HeadStyle(verticalAlignment = VerticalAlignmentEnum.CENTER)
public class ExportDimensionExcelVO {

    @ColumnWidth(10)
    @ExcelProperty(value = "序号")
    private Integer number;

    @ColumnWidth(20)
    @ExcelProperty(value = "舆论场")
    private String situation;

    @ColumnWidth(20)
    @ExcelProperty(value = "站点")
    private String msiteName;

    @ColumnWidth(20)
    @ExcelProperty(value = "栏目")
    private String mboardName;

    @ColumnWidth(20)
    @ExcelProperty(value = "账号|作者")
    private String uname;

}
