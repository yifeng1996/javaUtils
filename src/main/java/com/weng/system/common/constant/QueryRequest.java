package com.weng.system.common.constant;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 *
 */
@Data
@ToString
public class QueryRequest implements Serializable {

    private static final long serialVersionUID = -4869594085374385813L;

    /**
     * 当前页面数据量
     */
    private int pageSize = 10;

    /**
     * 当前页码
     */
    private int pageNum = 1;

    /**
     * 排序字段
     */
    private String field;

    /**
     * 排序规则，asc升序，desc降序
     */
    private String order;

    public int getPageSize() {
/*        if (pageSize < 0 || pageSize > 1000) {
            throw new RuntimeException("当前页面数据量控制在1～1000之间");
        }*/
        return pageSize;
    }

    public int getPageNum() {
        if (pageNum < 0) {
            throw new RuntimeException("当前页码不能小于0");
        }
        return pageNum;
    }
}
