package com.weng.system.common.configure;

import java.util.HashMap;

public class ResponseBo<T> extends HashMap<String, Object> {

	private static final long serialVersionUID = -8713837118340960775L;

	// 成功-编号
	private static final Integer SUCCESS_CODE = 200;
	// 警告-编号
	private static final Integer WARN_CODE = 1;
	// 异常 失败-编号
	private static final Integer FAIL_CODE = 500;

	// 未登录
	private static final Integer NO_LOGIN= -1;

	public ResponseBo() {
		put("code", SUCCESS_CODE);
		put("message", "操作成功");
	}

	public static <T> ResponseBo<T> error(Object msg) {
		ResponseBo<T> responseBo = new ResponseBo<T>();
		responseBo.put("code", FAIL_CODE);
		responseBo.put("message", msg);
		return responseBo;
	}

	public static <T> ResponseBo<T> noLogin(Object msg) {
		ResponseBo<T> responseBo = new ResponseBo<T>();
		responseBo.put("code", NO_LOGIN);
		responseBo.put("message", msg);
		return responseBo;
	}

	public static <T> ResponseBo<T> warn(Object msg) {
		ResponseBo<T> responseBo = new ResponseBo<T>();
		responseBo.put("code", WARN_CODE);
		responseBo.put("message", msg);
		return responseBo;
	}

	public static <T> ResponseBo<T> success(Object msg) {
		ResponseBo<T> responseBo = new ResponseBo<T>();
		responseBo.put("code", SUCCESS_CODE);
		responseBo.put("message", msg);
		return responseBo;
	}

	public static <T> ResponseBo<T> success() {
		return new ResponseBo<T>();
	}

	public static <T> ResponseBo<T> error() {
		return ResponseBo.error("");
	}

	public ResponseBo<T> message(String message) {
		this.put("message", message);
		return this;
	}

	@Override
	public ResponseBo<T> put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public ResponseBo<T> data(Object data) {
		this.put("data", data);
		return this;
	}
}
