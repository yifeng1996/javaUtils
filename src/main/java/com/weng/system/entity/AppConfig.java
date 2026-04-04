package com.weng.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 配置表
 */
@Data
@TableName("app_config")
public class AppConfig implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "app_config_id", type = IdType.AUTO)
    private Long appConfigId;

    /**
     * 描述
     */
    @TableField("config_desc")
    @Size(max = 200, message = "描述不能超过200长度")
    private String configDesc;

    /**
     * 键
     */
    @TableField("config_name")
    @NotBlank(message = "键值必填项")
    @Size(min = 5, max = 200, message = "键长度5~200字符")
    private String configName;

    /**
     * 值
     */
    @TableField("config_value")
    private String configValue;

    /**
     * 创建者id
     */
    @TableField("creater_id")
    private Long createrId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 创建用户名
     */
    @TableField("creater_user_name")
    private String createrUserName;

}
