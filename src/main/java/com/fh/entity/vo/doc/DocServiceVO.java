package com.fh.entity.vo.doc;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fh.entity.system.doc.DocService;
import com.fh.entity.system.doc.DocUser;
import com.fh.util.Const;

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
	@ApiModelProperty(value = "图片路径")
	private String docImg;
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

	public String getDocImg() {
		if(StringUtils.isBlank(docImg)){
			docImg="empty.png";
		}
		docImg=Const.APP_URL+Const.FILEPATHIMG+docImg;
		return docImg;
	}

	public void setDocImg(String docImg) {
		this.docImg = docImg;
	}
	
	
	
}
