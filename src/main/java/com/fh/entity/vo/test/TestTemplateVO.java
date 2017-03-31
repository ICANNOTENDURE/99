package com.fh.entity.vo.test;

import java.math.BigDecimal;
import java.util.List;

import com.fh.entity.test.AppTestTemplate;


public class TestTemplateVO {
	
	private String id;
	private String hopName;
	private String testName;
	private BigDecimal price;
	private String status;
	private List<AppTestTemplate> templates;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<AppTestTemplate> getTemplates() {
		return templates;
	}
	public void setTemplates(List<AppTestTemplate> templates) {
		this.templates = templates;
	}
	
	
	
}
