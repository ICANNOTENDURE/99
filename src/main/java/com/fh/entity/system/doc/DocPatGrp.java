package com.fh.entity.system.doc;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import javax.persistence.Table;

import com.fh.entity.BaseEntity;

@Table(name="doc_patgrp")
public class DocPatGrp extends BaseEntity{
	
	@Id
	@ApiModelProperty(name="分组id")
	private String grpId;
	@ApiModelProperty(name="分组名称")
	private String grpName;
	@ApiModelProperty(hidden=true)
	private String grpDocId;
	public String getGrpId() {
		return grpId;
	}
	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}
	public String getGrpName() {
		return grpName;
	}
	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}
	public String getGrpDocId() {
		return grpDocId;
	}
	public void setGrpDocId(String grpDocId) {
		this.grpDocId = grpDocId;
	}
	
	
	
}
