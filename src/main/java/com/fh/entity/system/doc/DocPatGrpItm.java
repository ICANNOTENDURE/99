package com.fh.entity.system.doc;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fh.entity.BaseEntity;

@Table(name="doc_patgrpitm")
public class DocPatGrpItm extends BaseEntity{
	
	@Id
	private String grpitmId;
	private String grpitmParent;
	private String grpitmFamId;
	@Transient
	private String famName;
	public String getGrpitmId() {
		return grpitmId;
	}
	public void setGrpitmId(String grpitmId) {
		this.grpitmId = grpitmId;
	}
	public String getGrpitmParent() {
		return grpitmParent;
	}
	public void setGrpitmParent(String grpitmParent) {
		this.grpitmParent = grpitmParent;
	}
	public String getGrpitmFamId() {
		return grpitmFamId;
	}
	public void setGrpitmFamId(String grpitmFamId) {
		this.grpitmFamId = grpitmFamId;
	}
	public String getFamName() {
		return famName;
	}
	public void setFamName(String famName) {
		this.famName = famName;
	}
	
	
	
	
}
