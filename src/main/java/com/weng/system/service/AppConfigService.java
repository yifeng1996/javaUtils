package com.weng.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.weng.system.common.constant.QueryRequest;
import com.weng.system.entity.AppConfig;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 配置表接口
 */
public interface AppConfigService extends IService<AppConfig> {
    /**
     * 查询(所有)
     *
     * @param appConfig
     * @return List<AppConfig>
     */
    List<AppConfig> list(AppConfig appConfig);

    /**
     * 查询(分页)
     *
     * @param request   QueryRequest
     * @param appConfig
     * @return IPage<AppConfig>
     */
    IPage<AppConfig> listPage(QueryRequest request, AppConfig appConfig);

    void exportExcel(HttpServletResponse httpServletResponse) throws Exception;
}
