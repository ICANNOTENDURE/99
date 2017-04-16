package com.fh.entity.test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
@Table(name="APP_TEST_RECORD")
public class AppTestRecord implements Serializable{
	
	/**
	 * 
	 */@Transient
	private static final long serialVersionUID = -2714259307417714269L;
	@Id
	private String id;
	
	private String resourceId;
	
	private Date date;
	
	private Integer qty;
	
	private Integer appointentQty;
	
	private BigDecimal price;
	@Transient
	private String hopName;
	@Transient
	private String testName;
	
	
	
	public String getHopName() {
		return hopName;
	}

	public void setHopName(String hopName) {
		this.hopName = hopName;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getAppointentQty() {
		return appointentQty==null?0:appointentQty;
	}

	public void setAppointentQty(Integer appointentQty) {
		this.appointentQty = appointentQty;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
