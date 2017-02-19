package com.fh.entity.vo.pat;

import io.swagger.annotations.ApiModelProperty;

public class LoginInfoVO {
	
	@ApiModelProperty(value = "账号")
	private String account;
	@ApiModelProperty(value = "头像")
	private String img;
	@ApiModelProperty(value = "余额")
	private Float amt;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Float getAmt() {
		return amt;
	}
	public void setAmt(Float amt) {
		this.amt = amt;
	}
	
	
}	
