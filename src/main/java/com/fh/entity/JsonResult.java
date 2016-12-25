package com.fh.entity;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;



public class JsonResult<T> {
	
	@ApiModelProperty(value = "返回结果值,0成功,其他失败", required = true)
	private int code=0;
	@ApiModelProperty(value = "返回消息", required = true)
	private String message;
	@ApiModelProperty(value = "返回数据集", required = true)
	private List<T> datas;
	
	
	
	
	
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	public JsonResult() {
		super();
	}
	public JsonResult(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
