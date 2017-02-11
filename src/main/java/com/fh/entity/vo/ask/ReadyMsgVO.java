package com.fh.entity.vo.ask;

import io.swagger.annotations.ApiModelProperty;

public class ReadyMsgVO {

	@ApiModelProperty(value = "医生姓名")
	private String docName;
	@ApiModelProperty(value = "提问表id")
	private String askId;
	@ApiModelProperty(value = "医生头像图片")
	private String docImg;
	@ApiModelProperty(value = "病人头像图片")
	private String patImg;
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getAskId() {
		return askId;
	}
	public void setAskId(String askId) {
		this.askId = askId;
	}
	public String getDocImg() {
		return docImg;
	}
	public void setDocImg(String docImg) {
		this.docImg = docImg;
	}
	public String getPatImg() {
		return patImg;
	}
	public void setPatImg(String patImg) {
		this.patImg = patImg;
	}
	
	
}
