package com.fh.entity.vo.doc;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fh.entity.system.doc.DocService;
import com.fh.util.Const;

public class DocInfoDetailVO {
	
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
	@ApiModelProperty(value = "擅长疾病")
	private String docDisease;
	@ApiModelProperty(value = "医生简介")
	private String docIntroduce;
	@ApiModelProperty(value = "医生服务病人数")
	private Integer docServerNum;
	@ApiModelProperty(value = "医生回答次数")
	private Integer docReplyNum;
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
		docImg=Const.APP_IMG_PATH+docImg;
		return docImg;
	}

	public void setDocImg(String docImg) {
		this.docImg = docImg;
	}
	
	
	
}
