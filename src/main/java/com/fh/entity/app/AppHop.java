package com.fh.entity.app;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fh.entity.BaseEntity;

@Table(name="APP_HOP")
public class AppHop extends BaseEntity{
	
	@Id
	private String hopId;
	private String hopName;
	private String hopLevel;
	private String hopStatus;
	@Transient
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
