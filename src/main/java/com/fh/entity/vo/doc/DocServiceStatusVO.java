package com.fh.entity.vo.doc;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class DocServiceStatusVO {
	
	@ApiModelProperty(value = "医生服务认证状态,0:待认证,1:审核中,2:审核通过,3:审核拒绝,4:暂停")
	private String serviceStatus;
	@ApiModelProperty(value = "医生服务名称")
	private String serviceName;
	@ApiModelProperty(value = "医生服务价格")
	private BigDecimal servicePrice;
	@ApiModelProperty(value = "医生服务id")
	private String serviceId;
	public String getServiceStatus() {
		return serviceStatus;
	}
	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public BigDecimal getServicePrice() {
		return servicePrice;
	}
	public void setServicePrice(BigDecimal servicePrice) {
		this.servicePrice = servicePrice;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	
	
	
}
