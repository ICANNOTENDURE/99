package com.fh.entity.system.doc;

import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fh.entity.BaseEntity;

@Table(name="DOC_SERVICE")
public class DocService extends BaseEntity{
	
	@Id
	private String serviceId;
	private String docinfoId;
	private String serviceType;
	private BigDecimal servicePrice;
	@Transient
	private String serviceTypeName;
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
	
	
}
