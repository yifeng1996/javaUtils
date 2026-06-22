package com.weng.system.lab;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class 生成文件的文件头 {

	public static String bytesToHexString(String path) {
		StringBuilder stringBuilder = new StringBuilder();
		FileInputStream is = null;
		try {
			is = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		byte[] b = new byte[4];// 大小不同，获取的文件头长度也不一样
		try {
			is.read(b, 0, b.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (b == null || b.length <= 0) {
			return null;
		}
		for (int i = 0; i < b.length; i++) {
			// 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式
			int v = b[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		// 转换为大写输出
		return stringBuilder.toString().toUpperCase();
	}

	public static void main(String[] args) throws Exception {
		System.out.println(bytesToHexString("C:/Users/73490/Desktop/20190402192737927.ceb"));
	}
	
	//466F756E   

}
