package com.weng.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 文件日志
 * 
 */
@Data
@AllArgsConstructor
public class Test implements Serializable {

	Long id;

	String element;
}
