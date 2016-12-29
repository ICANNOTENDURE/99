package com.fh.dto.system;

import java.util.List;

import com.fh.entity.system.Dictionaries;
import com.fh.entity.system.doc.DocInfo;
import com.fh.entity.system.doc.DocUser;

public class DocUserDto {

	private DocUser docUser;
	private DocInfo docInfo;
	private String diseasIds;
	private List<Dictionaries> doctitle;
	
	private String diseaseSelect;
	private String diseaseIds;
	
	
	
	
	

	public String getDiseaseSelect() {
		return diseaseSelect;
	}
	public void setDiseaseSelect(String diseaseSelect) {
		this.diseaseSelect = diseaseSelect;
	}
	public String getDiseaseIds() {
		return diseaseIds;
	}
	public void setDiseaseIds(String diseaseIds) {
		this.diseaseIds = diseaseIds;
	}
	public List<Dictionaries> getDoctitle() {
		return doctitle;
	}
	public void setDoctitle(List<Dictionaries> doctitle) {
		this.doctitle = doctitle;
	}
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
