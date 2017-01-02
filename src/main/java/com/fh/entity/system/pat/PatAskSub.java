package com.fh.entity.system.pat;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.fh.entity.BaseEntity;


@Table(name="PAT_ASK_SUB")
public class PatAskSub extends BaseEntity{
	
	@Id
	private String asksubId;
	private String parentId;
	private Date asksubDate;
	private String asksubContent;
	/*
	 * PAT:病人提问
	 * DOC:医生回答
	 */
	private String asksubType;
	private String asksubMessageType;
	private String asksubPath;
	public String getAsksubId() {
		return asksubId;
	}
	public void setAsksubId(String asksubId) {
		this.asksubId = asksubId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public Date getAsksubDate() {
		return asksubDate;
	}
	public void setAsksubDate(Date asksubDate) {
		this.asksubDate = asksubDate;
	}
	public String getAsksubContent() {
		return asksubContent;
	}
	public void setAsksubContent(String asksubContent) {
		this.asksubContent = asksubContent;
	}
	public String getAsksubType() {
		return asksubType;
	}
	public void setAsksubType(String asksubType) {
		this.asksubType = asksubType;
	}
	public String getAsksubMessageType() {
		return asksubMessageType;
	}
	public void setAsksubMessageType(String asksubMessageType) {
		this.asksubMessageType = asksubMessageType;
	}
	public String getAsksubPath() {
		return asksubPath;
	}
	public void setAsksubPath(String asksubPath) {
		this.asksubPath = asksubPath;
	}
	
	
	
	
	
}