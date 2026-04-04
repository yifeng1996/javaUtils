package com.weng.system.common.httpexecutor;

/**
 * 尝试结果信息
 */
public class RetryException extends RuntimeException {

	private Object result;

	private Exception exception;

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public void clear() {
		this.result = null;
		this.exception = null;
	}
}
