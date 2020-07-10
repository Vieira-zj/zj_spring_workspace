package com.zhengjin.springmvc4.web.ch4_3;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhengjin.springmvc4.domain.DemoObj;

/**
 * Controller注解 + ResponseBody注解
 *
 */
@Controller
@RequestMapping("/anno")
public class DemoAnnoController {

	// path: /anno
	@RequestMapping(produces = "text/plain;charset=UTF-8")
	public @ResponseBody String index(HttpServletRequest request) {
		return "url: " + request.getRequestURL() + " can access";
	}

	// path: /anno/pathvar/xx
	@RequestMapping(value = "/pathvar/{str}", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String demoPathVar(@PathVariable String str, HttpServletRequest request) {
		return "url: " + request.getRequestURL() + " can access, str: " + str;
	}

	// path: /anno/requestParam?id=1
	@RequestMapping(value = "/requestParam", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String passRequestParam(Long id, HttpServletRequest request) {
		return "url: " + request.getRequestURL() + " can access, id: " + id;
	}

	// path: /anno/obj?id=3&name=yy
	@RequestMapping(value = "/obj", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String passObject(DemoObj obj, HttpServletRequest request) {
		return String.format("url: %s can access, obj id: %d object name: %s", request.getRequestURL(), obj.getId(),
				obj.getName());
	}

	// path: /anno/name1, /anno/name2
	@RequestMapping(value = { "/name1", "/name2" }, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String remove(HttpServletRequest request) {
		return "url: " + request.getRequestURL() + " can access";
	}

}
