package com.fh.entity.vo.doc;

import java.util.List;

import com.fh.entity.system.doc.DocService;
import com.fh.entity.system.doc.DocUser;

public class DocServiceVO {

	private String infoId;
	private String hopName;
	private String locName;
	private String docTitle;
	private String docName;
	private DocUser docUser;
	
	private List<DocService> docServices;

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
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

	public String getDocTitle() {
		return docTitle;
	}

	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}

	public DocUser getDocUser() {
		return docUser;
	}

	public void setDocUser(DocUser docUser) {
		this.docUser = docUser;
	}

	public List<DocService> getDocServices() {
		return docServices;
	}

	public void setDocServices(List<DocService> docServices) {
		this.docServices = docServices;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}
	
	
	
}
