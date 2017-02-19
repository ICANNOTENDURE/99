package com.fh.entity.vo.pat;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class EvaluteVO {
	
	@ApiModelProperty(value = "评价日期")
	private Date createDate;
	@ApiModelProperty(value = "医生回复速度")
	private Integer speed;
	@ApiModelProperty(value = "态度")
	private Integer attitude;
	@ApiModelProperty(value = "治疗效果")
	private Integer effect;
	@ApiModelProperty(value = "评价内容")
	private String content;
	@ApiModelProperty(value = "账号")
	private String account;
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Integer getSpeed() {
		return speed;
	}
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	public Integer getAttitude() {
		return attitude;
	}
	public void setAttitude(Integer attitude) {
		this.attitude = attitude;
	}
	public Integer getEffect() {
		return effect;
	}
	public void setEffect(Integer effect) {
		this.effect = effect;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	
	
	
}
