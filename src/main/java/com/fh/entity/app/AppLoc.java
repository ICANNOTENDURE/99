package com.fh.entity.app;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jeecgframework.poi.excel.annotation.Excel;

import com.fh.entity.BaseEntity;

@Table(name="APP_LOC")
public class AppLoc extends BaseEntity{
	
	@Id
	private String locId;
	@Excel(name = "名称", orderNum = "1")
	private String locName;
	private String locStatus;
	private String locParent;
	@Transient
	private List<AppLoc> nodes=new ArrayList<AppLoc>();
	@Transient
	private String url;
	@Transient
	private String target;
	
	
	
	
	
	public AppLoc() {
		super();
	}
	public AppLoc(String locName, String locStatus) {
		super();
		this.locName = locName;
		this.locStatus = locStatus;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<AppLoc> getNodes() {
		return nodes;
	}
	public void setNodes(List<AppLoc> nodes) {
		this.nodes = nodes;
	}
	public String getLocId() {
		return locId;
	}
	public void setLocId(String locId) {
		this.locId = locId;
	}
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
	public String getLocStatus() {
		return locStatus;
	}
	public void setLocStatus(String locStatus) {
		this.locStatus = locStatus;
	}
	public String getLocParent() {
		return locParent;
	}
	public void setLocParent(String locParent) {
		this.locParent = locParent;
	}
	
	
}
