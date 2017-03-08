package com.fh.entity.vo.doc;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class DocInfoStatusVO {
	
	@ApiModelProperty(value = "医生资质认证状态,0:待认证,1:审核中,2:审核通过,3:审核拒绝,4:暂停")
	private String docQualifyStatus;
	@ApiModelProperty(value = "医生服务")
	private List<DocServiceStatusVO> docServiceStatusVOs;
	public String getDocQualifyStatus() {
		return docQualifyStatus;
	}
	public void setDocQualifyStatus(String docQualifyStatus) {
		this.docQualifyStatus = docQualifyStatus;
	}
	public List<DocServiceStatusVO> getDocServiceStatusVOs() {
		return docServiceStatusVOs;
	}
	public void setDocServiceStatusVOs(List<DocServiceStatusVO> docServiceStatusVOs) {
		this.docServiceStatusVOs = docServiceStatusVOs;
	}

}
