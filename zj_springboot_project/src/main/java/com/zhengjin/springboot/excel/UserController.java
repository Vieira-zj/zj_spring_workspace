package com.zhengjin.springboot.excel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zhengjin.springboot.utils.ValidationUtils;

@RestController
@RequestMapping("/users")
public final class UserController {

	// curl -v http://localhost:8081/helloboot/users/test
	@RequestMapping(path = "/test", method = RequestMethod.GET)
	public ResponseMsg test() throws IOException {
		return new ResponseMsg(0, "success");
	}

	// curl -v http://localhost:8081/helloboot/users/createExcel -o easyexcel.xls
	@RequestMapping(path = "/createExcel", method = RequestMethod.GET)
	public void createExcel(HttpServletResponse response) throws IOException {
		XSSFWorkbook workbook = null;
		OutputStream outputStream = null;

		try {
			workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet();
			Row titleRow = sheet.createRow(0);

			XSSFCellStyle titleStyle = workbook.createCellStyle();
			titleStyle.setAlignment(HorizontalAlignment.CENTER);
			titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			titleStyle.setFillForegroundColor(IndexedColors.YELLOW.index);

			String[] columnNames = { "用户名", "年龄", "手机号", "性别" };
			for (int i = 0; i < columnNames.length; i++) {
				Cell cell = titleRow.createCell(i);
				cell.setCellValue(columnNames[i]);
				cell.setCellStyle(titleStyle);
			}

			List<UserExcelModel> dataList = new ArrayList<>();
			dataList.add(new UserExcelModel("用户1", 12, "13867098765", "男"));
			dataList.add(new UserExcelModel("用户2", 13, "13867098766", "男"));
			dataList.add(new UserExcelModel("用户3", 14, "13867098767", "女"));
			dataList.add(new UserExcelModel("用户4", 15, "13867098768", "女"));

			for (UserExcelModel userExcelModel : dataList) {
				int lastRowNum = sheet.getLastRowNum();
				Row dataRow = sheet.createRow(lastRowNum + 1);
				dataRow.createCell(0).setCellValue(userExcelModel.getName());
				dataRow.createCell(1).setCellValue(userExcelModel.getAge());
				dataRow.createCell(2).setCellValue(userExcelModel.getMobile());
				dataRow.createCell(3).setCellValue(userExcelModel.getSex());
			}

			response.setContentType("application/vnd.ms-excel");
			response.setHeader("content-Disposition",
					"attachment;filename=" + URLEncoder.encode("easyexcel.xls", "utf-8"));
			response.setHeader("Access-Control-Expose-Headers", "content-Disposition");
			outputStream = response.getOutputStream();
			workbook.write(outputStream);
		} finally {
			if (outputStream != null) {
				outputStream.flush();
				outputStream.close();
			}
			if (workbook != null) {
				workbook.close();
			}
		}
	}

	// curl -v http://localhost:8081/helloboot/users/exportExcel -o easyexcel.xls
	@RequestMapping(path = "/exportExcel", method = RequestMethod.GET)
	public void exportExcel(HttpServletResponse response) throws IOException {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		Workbook workbook = null;

		try {
			ClassPathResource classPathResource = new ClassPathResource("excelTemplates/easyexcel.xls");
			inputStream = classPathResource.getInputStream();
			workbook = new XSSFWorkbook(inputStream);

			response.setContentType("application/vnd.ms-excel");
			response.setHeader("content-Disposition",
					"attachment;filename=" + URLEncoder.encode("easyexcel.xls", "utf-8"));
			response.setHeader("Access-Control-Expose-Headers", "content-Disposition");

			outputStream = response.getOutputStream();
			workbook.write(outputStream);
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.flush();
				outputStream.close();
			}
			if (workbook != null) {
				workbook.close();
			}
		}
	}

	// curl -v -F "filename=@easyexcel.xls"
	// http://localhost:8081/helloboot/users/importExcel
	@RequestMapping(path = "/importExcel", method = RequestMethod.POST)
	public UserExcelVO importExcel(@RequestParam("filename") MultipartFile file) throws IOException {
		List<UserExcelModel> dataList = EasyExcel
				.read(file.getInputStream(), UserExcelModel.class, new ModelExcelListener()).sheet().doReadSync();

		List<UserExcelModel> fail = new ArrayList<>();
		for (UserExcelModel user : dataList) {
			Set<ConstraintViolation<UserExcelModel>> violations = ValidationUtils.getValidator().validate(user);
			if (violations.size() > 0) {
				fail.add(user);
			}
		}

		UserExcelVO userExcelVO = new UserExcelVO();
		userExcelVO.setFail(fail);
		dataList.removeAll(fail);
		userExcelVO.setSuccess(dataList);
		return userExcelVO;
	}

	@SuppressWarnings("unused")
	private static class ResponseMsg {

		private Integer returnCode;
		private String message;

		public ResponseMsg(Integer returnCode, String message) {
			this.returnCode = returnCode;
			this.message = message;
		}

		public Integer getReturnCode() {
			return this.returnCode;
		}

		public void setReturnCode(Integer returnCode) {
			this.returnCode = returnCode;
		}

		public String getMessage() {
			return this.message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}

	private static class ModelExcelListener extends AnalysisEventListener<UserExcelModel> {

		private List<UserExcelModel> list = new ArrayList<>();

		@Override
		public void invoke(UserExcelModel data, AnalysisContext context) {
			// 数据存储到list, 后续业务逻辑处理
			list.add(data);
		}

		@Override
		public void doAfterAllAnalysed(AnalysisContext context) {
			System.out.println("所有数据解析完成");
		}
	}

}
