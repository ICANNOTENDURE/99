package com.fh.entity.vo.ask;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class PatAskVO {

	@ApiModelProperty(value = "医生姓名")
	private String docName;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
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
	
	
}
