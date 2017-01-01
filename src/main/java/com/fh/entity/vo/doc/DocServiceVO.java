package com.fh.entity.vo.doc;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fh.entity.system.doc.DocService;
import com.fh.entity.system.doc.DocUser;

public class DocServiceVO {
	
	@ApiModelProperty(value = "医生id")
	private String infoId;
	@ApiModelProperty(value = "医院名称")
	private String hopName;
	@ApiModelProperty(value = "科室名称")
	private String locName;
	@ApiModelProperty(value = "医生职称")
	private String docTitle;
	@ApiModelProperty(value = "医生姓名")
	private String docName;
	@ApiModelProperty(hidden=true)
	@JsonIgnore
	private DocUser docUser;
	@ApiModelProperty(value = "医生服务类型")
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
