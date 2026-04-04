package com.weng.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 文件日志
 * 
 */
@Setter
@Getter
public class DocumentLog implements Serializable {

	private static final long serialVersionUID = -3904488806314290098L;

	private Integer id;
	/**
	 * 文件id
	 */
	private Integer documentId;
	/**
	 * 操作人
	 */
	private String userName;
	/**
	 * 操作人id
	 */
	private Integer userId;
	/**
	 * 方法名称
	 */
	private String methodName;
	/**
	 * 方法描述
	 */
	private String methodDescription;
	/**
	 * 操作备注
	 */
	private String remark;
	/**
	 * 请求开始时间
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date startTime;
	/**
	 * 操作结束时间
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date endTime;

	/**
	 * 访问者ip
	 */
	private String requestIp;
	/**
	 * 状态
	 */
	private String status;
	/**
	 * 异常编码
	 */
	private String exceptionCode;
	/**
	 * 异常详情
	 */
	private String exceptionDetail;


	public DocumentLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DocumentLog(Integer id, Integer documentId, String userName, Integer userId, String methodName,
                       String methodDescription, String remark, Date startTime, Date endTime,
                       String requestIp, String status, String exceptionCode, String exceptionDetail) {
		super();
		this.id = id;
		this.documentId = documentId;
		this.userName = userName;
		this.userId = userId;
		this.methodName = methodName;
		this.methodDescription = methodDescription;
		this.remark = remark;
		this.startTime = startTime;
		this.endTime = endTime;
		this.requestIp = requestIp;
		this.status = status;
		this.exceptionCode = exceptionCode;
		this.exceptionDetail = exceptionDetail;
	}

}
