package com.fh.entity.app;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jeecgframework.poi.excel.annotation.Excel;

import com.fh.entity.BaseEntity;

@Table(name="APP_LOC_DISEASE")
public class LocDisease extends BaseEntity{
	
	@Id
	private String locDiseaseId;
	private String locId;
	private String diseaseId;
	@Excel(name = "病种科目", orderNum = "1")
	@Transient
	private String locName;
	@Excel(name = "病种名称", orderNum = "2")
	@Transient
	private String diseaseName;
	
	
	
	
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
	public String getDiseaseName() {
		return diseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	public LocDisease() {
		super();
	}
	public LocDisease(String locId, String diseaseId) {
		super();
		this.locId = locId;
		this.diseaseId = diseaseId;
	}
	public String getLocDiseaseId() {
		return locDiseaseId;
	}
	public void setLocDiseaseId(String locDiseaseId) {
		this.locDiseaseId = locDiseaseId;
	}
	public String getLocId() {
		return locId;
	}
	public void setLocId(String locId) {
		this.locId = locId;
	}
	public String getDiseaseId() {
		return diseaseId;
	}
	public void setDiseaseId(String diseaseId) {
		this.diseaseId = diseaseId;
	}
	
	
	
}
