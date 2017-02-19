package com.fh.entity.vo.pat;

import io.swagger.annotations.ApiModelProperty;

import org.apache.commons.lang.StringUtils;

import com.fh.util.enums.DefaultImgEnum;

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
		if(StringUtils.isBlank(img)){
			img=DefaultImgEnum.PAT.getImagePath();
		}
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Float getAmt() { 
		return null==amt?0F:amt;
	}
	public void setAmt(Float amt) {
		this.amt = amt;
	}
	
	
}	
