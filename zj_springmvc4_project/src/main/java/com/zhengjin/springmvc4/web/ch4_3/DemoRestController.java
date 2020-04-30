package com.zhengjin.springmvc4.web.ch4_3;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zhengjin.springmvc4.domain.DemoObj;

@RestController
@RequestMapping("/rest")
public class DemoRestController {

	// path: /rest/getjson?id=1&name=xx
	@RequestMapping(value = "/getjson", produces = "application/json;charset=UTF-8")
	public DemoObj getjson(DemoObj obj) {
		Long id = obj.getId();
		// check null here, see ExceptionHandlerAdvice.initBinder for details
		if (id == null) {
			id = new Long(1);
		}
		return new DemoObj(new Long(id + 1), obj.getName() + "_json");
	}

	// path: /rest/getxml?id=2&name=yy
	@RequestMapping(value = "/getxml", produces = "application/xml;charset=UTF-8")
	public DemoObj getxml(DemoObj obj) {
		Long id = obj.getId();
		if (id == null) {
			id = new Long(1);
		}
		return new DemoObj(new Long(id + 1), obj.getName() + "_xml");
	}

}
