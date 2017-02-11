package com.fh.entity.vo.ask;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fh.util.enums.AskStatus;

public class PatAskVO {

	@ApiModelProperty(value = "医生姓名")
	private String docName;
	@ApiModelProperty(value = "病人姓名")
	private String patName;
	@ApiModelProperty(value = "提问标题")
	private String title;
	@ApiModelProperty(value = "提问内容")
	private String content;
	@ApiModelProperty(value = "提问日期")
	private Date createDate;
	@ApiModelProperty(value = "当前状态")
	private String status;
	@ApiModelProperty(value = "提问表id")
	private String askId;
	@ApiModelProperty(value = "医生头像图片路径")
	private String docImg;
	@ApiModelProperty(value = "病人头像图片路径")
	private String patImg;
	@ApiModelProperty(value = "金额")
	private String amt;
	
	
	public String getDocImg() {
		if(StringUtils.isBlank(docImg)){
			docImg="empty.png";
		}
		return docImg;
	}
	public void setDocImg(String docImg) {
		this.docImg = docImg;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return AskStatus.getValueByKey(status);
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAskId() {
		return askId;
	}
	public void setAskId(String askId) {
		this.askId = askId;
	}
	public String getAmt() {
		return amt;
	}
	public void setAmt(String amt) {
		this.amt = amt;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getPatImg() {
		return patImg;
	}
	public void setPatImg(String patImg) {
		this.patImg = patImg;
	}
	
	
}
