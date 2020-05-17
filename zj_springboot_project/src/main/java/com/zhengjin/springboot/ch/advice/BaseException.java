package com.zhengjin.springboot.ch.advice;

import java.util.HashMap;
import java.util.Map;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final ErrorCode error;
	private final HashMap<String, Object> data = new HashMap<>();

	public BaseException(ErrorCode error, Map<String, Object> data) {
		super(error.getMessage());
		this.error = error;
		if (data != null && data.size() > 0) {
			this.data.putAll(data);
		}
	}

	protected BaseException(ErrorCode error, Map<String, Object> data, Throwable cause) {
		super(error.getMessage(), cause);
		this.error = error;
		if (data != null && data.size() > 0) {
			this.data.putAll(data);
		}
	}

	public ErrorCode getError() {
		return this.error;
	}

	public Map<String, Object> getData() {
		return this.data;
	}

}
