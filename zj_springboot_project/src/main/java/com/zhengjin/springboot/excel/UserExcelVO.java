package com.zhengjin.springboot.excel;

import java.util.List;

public final class UserExcelVO {

	List<UserExcelModel> success;
	List<UserExcelModel> fail;

	public void setSuccess(List<UserExcelModel> success) {
		this.success = success;
	}

	public List<UserExcelModel> getSuccess() {
		return this.success;
	}

	public void setFail(List<UserExcelModel> fail) {
		this.fail = fail;
	}

	public List<UserExcelModel> getFail() {
		return this.fail;
	}

}
