package com.fh.entity.test;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Table(name="APP_TEST_TEMPLATE")
public class AppTestTemplate implements Serializable{
	
	/**
	 * 
	 */@Transient
	private static final long serialVersionUID = -2714259307417714269L;
	@Id
	private String id;
	
	private String day;
	
	private Integer qty;
	
	private String resourseId;
	
	private String flag;
	
	
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getResourseId() {
		return resourseId;
	}

	public void setResourseId(String resourseId) {
		this.resourseId = resourseId;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
	
	
}
