package com.fh.entity.system.doc;

import javax.persistence.Id;
import javax.persistence.Table;

import com.fh.entity.BaseEntity;

@Table(name="doc_special_disease")
public class DocSpecialDisease extends BaseEntity{
	
	@Id
	private String specialId;
	private String docinfoId;
	private String diseaseId;
	
	
	
	public DocSpecialDisease() {
		super();
	}
	
	

	public DocSpecialDisease(String specialId, String docinfoId,
			String diseaseId) {
		super();
		this.specialId = specialId;
		this.docinfoId = docinfoId;
		this.diseaseId = diseaseId;
	}



	public DocSpecialDisease(String docinfoId, String diseaseId) {
		super();
		this.docinfoId = docinfoId;
		this.diseaseId = diseaseId;
	}



	public String getSpecialId() {
		return specialId;
	}



	public void setSpecialId(String specialId) {
		this.specialId = specialId;
	}



	public String getDocinfoId() {
		return docinfoId;
	}


	public void setDocinfoId(String docinfoId) {
		this.docinfoId = docinfoId;
	}



	public String getDiseaseId() {
		return diseaseId;
	}



	public void setDiseaseId(String diseaseId) {
		this.diseaseId = diseaseId;
	}


}
