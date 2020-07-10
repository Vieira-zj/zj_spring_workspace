package com.zhengjin.springmvc4.web.ch4_5;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * SSE (Server Send Event), 服务器端推送技术。
 *
 */
@Controller
public class SseController {

	@RequestMapping(value = "/push", produces = "text/event-stream")
	public @ResponseBody String push() {
		Random r = new Random();
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "data:Testing 1,2,3," + r.nextInt(100) + "\n\n";
	}

}
