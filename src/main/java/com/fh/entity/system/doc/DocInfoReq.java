package com.fh.entity.system.doc;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fh.entity.BaseEntity;
import com.fh.util.enums.AuditDocStatusEnum;

@Table(name="DOC_INFO_REQ")
public class DocInfoReq extends BaseEntity{
	
	@Id
	private String id;
	private String reqInfoId;
	private Date createDate;
	private String status;
	private String content;
	private String userId;
	@Transient
	private String docName;
	@Transient
	private String hopName;
	@Transient
	private String locName;
	@Transient
	private String statusDesc;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReqInfoId() {
		return reqInfoId;
	}
	public void setReqInfoId(String reqInfoId) {
		this.reqInfoId = reqInfoId;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getHopName() {
		return hopName;
	}
	public void setHopName(String hopName) {
		this.hopName = hopName;
	}
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStatusDesc() {
		return AuditDocStatusEnum.getValueByKey(status);
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	
	
}
