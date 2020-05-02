package com.zhengjin.springboot_websocket.web;

import java.security.Principal;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.zhengjin.springboot_websocket.domain.JinMessage;
import com.zhengjin.springboot_websocket.domain.JinResponse;

@Controller
public class WsController {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	/**
	 * 广播消息 1. 当浏览器向服务端发送请求时，通过@MessageMapping映射 /welcome 这个地址。 2.
	 * 当服务端有消息时，会对订阅了@SendTo中的路径的浏览器发送消息。
	 * 
	 * @param message
	 * @return
	 * @throws InterruptedException
	 */
	@MessageMapping("/welcome")
	@SendTo("/topic/getResponse")
	public JinResponse say(JinMessage message) throws InterruptedException {
		TimeUnit.SECONDS.sleep(3L);
		return new JinResponse("Welcome, " + message.getName() + "!");
	}

	/**
	 * 点对点消息
	 * 
	 * @param principal 包含当前用户的信息。
	 * @param msg
	 */
	@MessageMapping("/chat")
	public void handleChat(Principal principal, String msg) {
		if (principal.getName().equals("zhengjin")) {
			messagingTemplate.convertAndSendToUser("spring", "/queue/notifications",
					principal.getName() + "-send:" + msg);
		} else {
			messagingTemplate.convertAndSendToUser("zhengjin", "/queue/notifications",
					principal.getName() + "-send:" + msg);
		}
	}

}
