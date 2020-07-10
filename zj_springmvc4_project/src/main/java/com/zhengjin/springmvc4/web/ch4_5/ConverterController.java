package com.zhengjin.springmvc4.web.ch4_5;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhengjin.springmvc4.domain.DemoObj;

/**
 * 通过自定义媒体类型处理输入与输出流。（MyMessageConverter.class）
 *
 */
@Controller
public class ConverterController {

	@RequestMapping(value = "/convert", produces = { "application/x-zhengjin" })
	public @ResponseBody DemoObj convert(@RequestBody DemoObj demoObj) {
		return demoObj;
	}

}
