package com.weng.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 */
public class BaseController {

	protected Map<String, Object> getDataTable(IPage<?> pageInfo) {
		return getDataTable(pageInfo, 5);
	}

	protected Map<String, Object> getDataTable(IPage<?> pageInfo, int dataMapInitialCapacity) {
		Map<String, Object> data = new HashMap<>(dataMapInitialCapacity);
		data.put("rows", pageInfo.getRecords());
		data.put("total", pageInfo.getTotal());
		data.put("pageSize", pageInfo.getSize());
		data.put("pageNum", pageInfo.getCurrent());
		data.put("totalPages", pageInfo.getPages());
		return data;
	}

	/*时间参数接收统一处理*/
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

}
