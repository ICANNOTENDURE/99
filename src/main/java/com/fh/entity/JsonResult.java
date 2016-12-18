package com.fh.entity;

public class JsonResult {

	private int code=0;
	private String message;
	

	
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
