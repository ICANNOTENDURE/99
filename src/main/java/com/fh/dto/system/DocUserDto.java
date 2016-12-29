package com.fh.dto.system;

import com.fh.entity.system.doc.DocInfo;
import com.fh.entity.system.doc.DocUser;

public class DocUserDto {

	private DocUser docUser;
	private DocInfo docInfo;
	private String diseasIds;
	
	
	
	public String getDiseasIds() {
		return diseasIds;
	}
	public void setDiseasIds(String diseasIds) {
		this.diseasIds = diseasIds;
	}
	public DocUser getDocUser() {
		return docUser;
	}
	public void setDocUser(DocUser docUser) {
		this.docUser = docUser;
	}
	public DocInfo getDocInfo() {
		return docInfo;
	}
	public void setDocInfo(DocInfo docInfo) {
		this.docInfo = docInfo;
	}
	
	
}
