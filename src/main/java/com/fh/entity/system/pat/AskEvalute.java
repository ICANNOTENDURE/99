package com.fh.entity.system.pat;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.fh.entity.BaseEntity;


@Table(name="pat_ask_evaluate")
public class AskEvalute extends BaseEntity{
	
	@Id
	private String evalId;
	private String parentId;
	private String docinfoId;
	private int evalAttitude;
	private int evalEffect;
	private int evalReplySpeed;
	private String evalContent;
	private Date createDate;
	private String patId;
	public String getEvalId() {
		return evalId;
	}
	public void setEvalId(String evalId) {
		this.evalId = evalId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getDocinfoId() {
		return docinfoId;
	}
	public void setDocinfoId(String docinfoId) {
		this.docinfoId = docinfoId;
	}
	public int getEvalAttitude() {
		return evalAttitude;
	}
	public void setEvalAttitude(int evalAttitude) {
		this.evalAttitude = evalAttitude;
	}
	public int getEvalEffect() {
		return evalEffect;
	}
	public void setEvalEffect(int evalEffect) {
		this.evalEffect = evalEffect;
	}
	public int getEvalReplySpeed() {
		return evalReplySpeed;
	}
	public void setEvalReplySpeed(int evalReplySpeed) {
		this.evalReplySpeed = evalReplySpeed;
	}
	public String getEvalContent() {
		return evalContent;
	}
	public void setEvalContent(String evalContent) {
		this.evalContent = evalContent;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getPatId() {
		return patId;
	}
	public void setPatId(String patId) {
		this.patId = patId;
	}
	
	
	
}