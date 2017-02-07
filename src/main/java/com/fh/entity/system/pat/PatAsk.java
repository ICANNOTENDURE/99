package com.fh.entity.system.pat;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.fh.entity.BaseEntity;


@Table(name="PAT_ASK")
public class PatAsk extends BaseEntity{
	
	@Id
	private String askId;
	private String askTitle;
	private String askContent;
	//
	private String askPatid;
	private String askFamid;
	//DocInfo
	private String askDocid;
	private Date askDate;
	private String askEvalFlag;
	private String askStatus;
	private String askDocRead;
	private String askPatRead;
	
	
	
	
	public String getAskTitle() {
		return askTitle;
	}
	public void setAskTitle(String askTitle) {
		this.askTitle = askTitle;
	}
	public String getAskId() {
		return askId;
	}
	public void setAskId(String askId) {
		this.askId = askId;
	}
	public String getAskContent() {
		return askContent;
	}
	public void setAskContent(String askContent) {
		this.askContent = askContent;
	}
	public String getAskPatid() {
		return askPatid;
	}
	public void setAskPatid(String askPatid) {
		this.askPatid = askPatid;
	}
	public String getAskFamid() {
		return askFamid;
	}
	public void setAskFamid(String askFamid) {
		this.askFamid = askFamid;
	}
	public String getAskDocid() {
		return askDocid;
	}
	public void setAskDocid(String askDocid) {
		this.askDocid = askDocid;
	}
	public Date getAskDate() {
		return askDate;
	}
	public void setAskDate(Date askDate) {
		this.askDate = askDate;
	}
	public String getAskEvalFlag() {
		return askEvalFlag;
	}
	public void setAskEvalFlag(String askEvalFlag) {
		this.askEvalFlag = askEvalFlag;
	}
	public String getAskStatus() {
		return askStatus;
	}
	public void setAskStatus(String askStatus) {
		this.askStatus = askStatus;
	}
	public String getAskDocRead() {
		return askDocRead;
	}
	public void setAskDocRead(String askDocRead) {
		this.askDocRead = askDocRead;
	}
	public String getAskPatRead() {
		return askPatRead;
	}
	public void setAskPatRead(String askPatRead) {
		this.askPatRead = askPatRead;
	}
	
	
	
}