package com.fh.entity.systemdoc;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.fh.entity.BaseEntity;

@Table(name="DOC_USER")
public class DocUser extends BaseEntity{
	
	@Id
	private String docId;
	private String docAccount;
	private String docPassword;
	private String status;
	private Date docLogindate; 
	
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getDocAccount() {
		return docAccount;
	}
	public void setDocAccount(String docAccount) {
		this.docAccount = docAccount;
	}
	public String getDocPassword() {
		return docPassword;
	}
	public void setDocPassword(String docPassword) {
		this.docPassword = docPassword;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDocLogindate() {
		return docLogindate;
	}
	public void setDocLogindate(Date docLogindate) {
		this.docLogindate = docLogindate;
	}
	
	
}
