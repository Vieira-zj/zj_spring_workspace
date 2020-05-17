package com.zhengjin.springboot.ch.advice;

import java.util.Map;

public class ResourceNotFoundException extends BaseException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Map<String, Object> data) {
		super(ErrorCode.RESOURCE_NOT_FOUND, data);
	}

}
