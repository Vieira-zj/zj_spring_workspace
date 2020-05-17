package com.zhengjin.springboot.ch.advice;

import org.springframework.http.HttpStatus;

/**
 * 枚举类中包含了异常的唯一标识、HTTP 状态码以及错误信息。
 * 
 * @author zhengjin
 *
 */
public enum ErrorCode {

	RESOURCE_NOT_FOUND(1001, HttpStatus.NOT_FOUND, "未找到该资源"),
	REQUEST_VALIDATION_FAILED(1002, HttpStatus.BAD_REQUEST, "请求数据格式验证失败");

	private final int code;
	private final HttpStatus status;
	private final String message;

	ErrorCode(int code, HttpStatus status, String message) {
		this.code = code;
		this.status = status;
		this.message = message;
	}

	public int getCode() {
		return this.code;
	}

	public HttpStatus getStatus() {
		return this.status;
	}

	public String getMessage() {
		return this.message;
	}

	@Override
	public String toString() {
		return String.format("ErrorCode{code=%d, status=%s, message=%s}", this.code, this.status, this.message);
	}

}
