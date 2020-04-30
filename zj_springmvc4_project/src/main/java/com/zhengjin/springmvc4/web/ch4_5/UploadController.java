package com.zhengjin.springmvc4.web.ch4_5;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String upload(MultipartFile file) {
		String savePath = "/tmp" + File.separator + file.getOriginalFilename();
		System.out.println("save file: " + savePath);
		try {
			FileUtils.writeByteArrayToFile(new File(savePath), file.getBytes());
			return "ok";
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
	}

}
