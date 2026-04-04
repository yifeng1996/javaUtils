/**
 * 2008-2-1
 */
package com.weng.system.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DispatchReturn {

    private String msg;

    private DataReturn data;

    private String issuccess;

    @Data
    public static class DataReturn{
        private String docid;
    }

}