package com.zhengjin.springboot.ch.advice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回给客户端具体的异常对象。
 * 
 */
public class ErrorResponse {

	private int code;
	private int status;
	private String message;
	private String path;
	private Instant timestamp;
	private Map<String, Object> data = new HashMap<>();

	public ErrorResponse() {
	}

	public ErrorResponse(BaseException ex, String path) {
		this(ex.getError().getCode(), ex.getError().getStatus().value(), ex.getError().getMessage(), path,
				ex.getData());
	}

	public ErrorResponse(int code, int status, String message, String path, Map<String, Object> data) {
		this.code = code;
		this.status = status;
		this.message = message;
		this.path = path;
		this.timestamp = Instant.now();
		if (data != null && data.size() > 0) {
			this.data.putAll(data);
		}
	}

	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Instant getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public Map<String, Object> getData() {
		return this.data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return String.format("ErrorReponse{code=%d, status=%d, message=%s, path=%s, timestamp=%s, data=%s}", this.code,
				this.status, this.message, this.path, this.timestamp, this.data);
	}

}
