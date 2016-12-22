package com.fh.entity.app;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jeecgframework.poi.excel.annotation.Excel;

import com.fh.entity.BaseEntity;

@Table(name="APP_DISEASE")
public class AppDisease extends BaseEntity{
	
	@Id
	private String diseaseId;
	@Excel(name = "病种名称", orderNum = "2")
	private String diseaseName;
	private String diseaseStatus;
	private String diseaseCode;
	@Excel(name = "临床表现", orderNum = "3")
	private String diseaseFeatures;
	
	@Excel(name = "病种科目", orderNum = "1")
	@Transient
	private String locName;
	
	
	
	
	public String getDiseaseFeatures() {
		return diseaseFeatures;
	}
	public void setDiseaseFeatures(String diseaseFeatures) {
		this.diseaseFeatures = diseaseFeatures;
	}
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
	public String getDiseaseId() {
		return diseaseId;
	}
	public void setDiseaseId(String diseaseId) {
		this.diseaseId = diseaseId;
	}
	public String getDiseaseName() {
		return diseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	public String getDiseaseStatus() {
		return diseaseStatus;
	}
	public void setDiseaseStatus(String diseaseStatus) {
		this.diseaseStatus = diseaseStatus;
	}
	public String getDiseaseCode() {
		return diseaseCode;
	}
	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	} 
	
	
	
	
	
	
}
