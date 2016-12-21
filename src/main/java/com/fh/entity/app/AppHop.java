package com.fh.entity.app;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import com.fh.entity.BaseEntity;

@ExcelTarget("appHop")
@Table(name="APP_HOP")
public class AppHop extends BaseEntity implements java.io.Serializable{
	
	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	private String hopId;
	@Excel(name = "医院名称", orderNum = "1")
	private String hopName;
	private String hopLevel;
	private String hopStatus;
	@Transient
	@Excel(name = "医疗机构级别", orderNum = "2")
	private String levelDesc;
	
	
	
	public String getHopStatus() {
		return hopStatus;
	}
	public void setHopStatus(String hopStatus) {
		this.hopStatus = hopStatus;
	}
	public String getLevelDesc() {
		return levelDesc;
	}
	public void setLevelDesc(String levelDesc) {
		this.levelDesc = levelDesc;
	}
	public String getHopId() {
		return hopId;
	}
	public void setHopId(String hopId) {
		this.hopId = hopId;
	}
	public String getHopName() {
		return hopName;
	}
	public void setHopName(String hopName) {
		this.hopName = hopName;
	}
	public String getHopLevel() {
		return hopLevel;
	}
	public void setHopLevel(String hopLevel) {
		this.hopLevel = hopLevel;
	}
	
	
	
}
