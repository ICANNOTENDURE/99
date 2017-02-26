package com.fh.entity.system.doc;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fh.entity.BaseEntity;
import com.fh.util.enums.AuditDocStatusEnum;

@Table(name="DOC_SERVICE")
public class DocService extends BaseEntity{
	
	@Id
	@ApiModelProperty(value = "服务id")
	private String serviceId;
	@ApiModelProperty(hidden=true)
	private String docinfoId;
	@ApiModelProperty(value = "服务类型id")
	private String serviceType;
	@ApiModelProperty(value = "服务价格")
	private BigDecimal servicePrice;
	@ApiModelProperty(hidden=true)
	private String auditFlag;
	@Transient
	@ApiModelProperty(value = "服务类型")
	private String serviceTypeName;
	@Transient
	private String auditFlagDesc;
	
	
	
	public String getAuditFlagDesc() {
		return AuditDocStatusEnum.getValueByKey(auditFlag);
	}
	public void setAuditFlagDesc(String auditFlagDesc) {
		this.auditFlagDesc = auditFlagDesc;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getDocinfoId() {
		return docinfoId;
	}
	public void setDocinfoId(String docinfoId) {
		this.docinfoId = docinfoId;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public BigDecimal getServicePrice() {
		return servicePrice;
	}
	public void setServicePrice(BigDecimal servicePrice) {
		this.servicePrice = servicePrice;
	}
	public String getServiceTypeName() {
		return serviceTypeName;
	}
	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}
	public String getAuditFlag() {
		return auditFlag;
	}
	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}
	
	
}
