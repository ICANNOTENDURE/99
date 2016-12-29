package com.fh.entity.systemdoc;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fh.entity.BaseEntity;

@Table(name="DOC_INFO")
public class DocInfo extends BaseEntity{
	
	@Id
	private String infoId;
	private String docId;
	private String docHopid;
	private String docLocid;
	private String docIntroduce;
	private String docTitle;
	private Integer docServerNum;
	private String docName;
	private Integer docReplyNum;
	private String docPic;
	private Integer docSeq;
	
	
	@Transient
	private String docAccount;
	@Transient
	private String status;
	@Transient
	private String hopName;
	@Transient
	private String locName;
	
	
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
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getDocHopid() {
		return docHopid;
	}
	public void setDocHopid(String docHopid) {
		this.docHopid = docHopid;
	}
	public String getDocLocid() {
		return docLocid;
	}
	public void setDocLocid(String docLocid) {
		this.docLocid = docLocid;
	}
	public String getDocIntroduce() {
		return docIntroduce;
	}
	public void setDocIntroduce(String docIntroduce) {
		this.docIntroduce = docIntroduce;
	}
	public String getDocTitle() {
		return docTitle;
	}
	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}
	public Integer getDocServerNum() {
		return docServerNum;
	}
	public void setDocServerNum(Integer docServerNum) {
		this.docServerNum = docServerNum;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public Integer getDocReplyNum() {
		return docReplyNum;
	}
	public void setDocReplyNum(Integer docReplyNum) {
		this.docReplyNum = docReplyNum;
	}
	public String getDocPic() {
		return docPic;
	}
	public void setDocPic(String docPic) {
		this.docPic = docPic;
	}
	public Integer getDocSeq() {
		return docSeq;
	}
	public void setDocSeq(Integer docSeq) {
		this.docSeq = docSeq;
	}
	public String getDocAccount() {
		return docAccount;
	}
	public void setDocAccount(String docAccount) {
		this.docAccount = docAccount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
