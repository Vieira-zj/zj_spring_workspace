package com.zhengjin.springmvc4.web.ch4_6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhengjin.springmvc4.service.DemoService;

@Controller
public class NormalController {

	@Autowired
	DemoService demoService;

	// for integration test
	@RequestMapping("/normal")
	public String testPage(Model model) {
		model.addAttribute("msg", demoService.saySomething());
		return "page";
	}

}
