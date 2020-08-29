package com.zhengjin.springboot.excel;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.alibaba.excel.annotation.ExcelProperty;

public final class UserExcelModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "用户名不能为空")
	@Size(max = 4)
	@ExcelProperty(value = "用户名", index = 0)
	private String name;

	@ExcelProperty(value = "年龄", index = 1)
	private Integer age;

	@NotNull(message = "手机号不能为空")
	@Pattern(regexp = "^[1][3,4,5,7,8][0-9]{9}$$", message = "手机号不合法")
	@ExcelProperty(value = "手机号", index = 2)
	private String mobile;

	@ExcelProperty(value = "性别", index = 3)
	private String sex;

	public UserExcelModel() {
	}

	public UserExcelModel(String name, Integer age, String mobile, String sex) {
		this.name = name;
		this.age = age;
		this.mobile = mobile;
		this.sex = sex;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSex() {
		return this.sex;
	}

}
