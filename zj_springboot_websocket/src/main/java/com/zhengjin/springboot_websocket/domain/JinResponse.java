package com.zhengjin.springboot_websocket.domain;

/**
 * 服务端向浏览器发送的消息类型。
 * 
 * @author zhengjin
 *
 */
public class JinResponse {

	private String responseMessage;

	public JinResponse(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getResponseMessage() {
		return this.responseMessage;
	}

}
