package com.weng.system.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGrid;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGridCol;

/**
 */
public class NavicatwordUtil {

	public static void main(String[] args) {
		try {
			createDoc("E:\\TRS\\wxb二期\\SQL文件\\高级设置-涉浙关键词.docx", "E:\\TRS\\wxb二期\\SQL文件\\高级设置-涉浙关键词.sql");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 创建文档
	private static void createDoc(String docDir, String sqlDir) throws IOException {
		// 创建新的word
		XWPFDocument doc = new XWPFDocument();
		// 从1.sql中拿
		String sql = getSql(sqlDir);
		// 截成表
		String[] creates = sql.split(";");
		for (int i = 0; i < creates.length; i++) {
			if (creates[i].contains("CREATE TABLE")) {
				insertItem(doc, creates[i]);
			}
		}
		try {
			// 导出到本地
			FileOutputStream out = new FileOutputStream(docDir);
			try {
				doc.write(out);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 得到sql内容
	private static String getSql(String dir) throws IOException {
		File file = new File(dir);
		String result = "";
		InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "UTF8");
		BufferedReader br = new BufferedReader(isr);// 构造一个BufferedReader类来读取文件
		String s = null;
		while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
			// 跳过注释信息
			if (s.contains("DROP TABLE IF EXISTS")) {
				continue;
			}
			if (s.contains("-- ------------------")) {
				continue;
			}
			if (s.contains("Table structure for")) {
				continue;
			}
			result = result + "\n" + s;
		}
		br.close();
		return new String(result);
	}

	// 插入行
	private static void insertItem(XWPFDocument doc, String sql) {
		// 创建段落
		XWPFParagraph p1 = doc.createParagraph();
		// 写入段落内容
		XWPFRun run = p1.createRun();
		run.setText("表名:" + getTableName(sql));
		// 插入字段
		insertCells(doc, sql);
	}

	// 得到表名和注释例如:`station_user`(站内信用户')
	private static String getTableName(String sql) {
		String result = null;
		if (sql.contains("CREATE TABLE `")) {
			result = sql.substring(14, sql.indexOf("(")).trim();
			if (sql.contains("COMMENT='")) {// 如果有表注释
				String s = sql.substring(sql.indexOf("COMMENT=") + 9, sql.length() - 1);
				s = "(" + s + ")";// 拼接注释
				result += s;
			}
		}
		return result;
	}

	private static void insertCells(XWPFDocument doc, String sql) {
		String[] split = sql.split(",");
		// 创建表格
		XWPFTable table = doc.createTable(split.length, 4);
		// 设置表格宽度
		setW(table, 5);
		// 第一行数据插入
		insertHeader(table);
		// 表字段数据插入
		for (int i = 0; i < split.length - 1; i++) {
			List<XWPFTableCell> tableCells = table.getRow(i + 1).getTableCells();
			// 遍历获得字段信息
			if (split[i].trim().length() > 0) {
				if (split[i].contains("CREATE TABLE `")) {
					split[i] = sql.substring(sql.indexOf("(") + 1, split[i].length());
				}
				String tmp = split[i].trim();
				if (!tmp.startsWith("`")) {// 不是以"`"开头，不是字段名称
					continue;
				}
				// 设置字段名
				String[] arr = tmp.split(" ");
				tableCells.get(0).setText(arr[0]);
				// 类型(长度)
				if (arr.length > 1) {
					tableCells.get(1).setText(arr[1]);
				}
				// 是否为null
				if (split[i].contains("NOT NULL")) {
					tableCells.get(2).setText("是");
				} else {
					tableCells.get(2).setText("否");
				}
				if (split[i].contains("COMMENT")) {
					// 备注
					tableCells.get(3).setText(split[i].substring(split[i].indexOf("COMMENT") + 9, split[i].length()));
				}

			}
		}
	}

	// 获取数据库字段信息
	private static String getFildType(String str) {
		if (str.contains("varchar") || str.contains("text") || str.contains("blob")) {
			return "字符串";
		} else if (str.contains("float") || str.contains("double") || str.contains("decimal")) {
			return "浮点";
		} else if (str.contains("int")) {
			return "整型";
		} else if (str.contains("datetime") || str.contains("time") || str.contains("timestamp")) {
			return "日期";
		}
		return "";
	}

	// 头部信息
	private static void insertHeader(XWPFTable table) {
		List<XWPFTableCell> tableCells = table.getRow(0).getTableCells();
		tableCells.get(0).setText("字段名称");
		tableCells.get(1).setText("类型(长度)");
		tableCells.get(2).setText("是否必填");
		tableCells.get(3).setText("备注");
	}

	// 初始化头部
	private static void setW(XWPFTable table, int colNum) {
		CTTbl ttbl = table.getCTTbl();
		CTTblGrid tblGrid = ttbl.getTblGrid() != null ? ttbl.getTblGrid() : ttbl.addNewTblGrid();
		for (int i = 0; i < colNum; i++) {
			CTTblGridCol gridCol = tblGrid.addNewGridCol();
			gridCol.setW(new BigInteger("" + 2000));
		}
		table.setCellMargins(50, 50, 50, 50);
	}

}
