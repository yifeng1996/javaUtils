package com.weng.system.controller;

import com.weng.system.common.configure.ResponseBo;
import com.weng.system.common.constant.QueryRequest;
import com.weng.system.entity.AppConfig;
import com.weng.system.service.AppConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 配置表控制层
 */
@Slf4j
@Validated
@Controller
@RequestMapping("appConfig")
public class AppConfigController extends BaseController {

    @Autowired
    private AppConfigService appConfigService;

    @GetMapping("list")
    @ResponseBody
    public ResponseBo<?> list(AppConfig appConfig) {
        return ResponseBo.success().data(appConfigService.list(appConfig));
    }

    @GetMapping("listPage")
    @ResponseBody
    public ResponseBo<?> listPage(QueryRequest request, AppConfig appConfig) {
        Map<String, Object> dataTable = getDataTable(appConfigService.listPage(request, appConfig));
        return ResponseBo.success().data(dataTable);
    }

    @GetMapping("exportExcel")
    @ResponseBody
    public void exportExcel(HttpServletResponse httpServletResponse) throws Exception{
        appConfigService.exportExcel(httpServletResponse);
    }

}
