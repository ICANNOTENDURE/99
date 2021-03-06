package com.fh.entity.app;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import com.alibaba.fastjson.annotation.JSONField;
import com.fh.entity.BaseEntity;

@ExcelTarget("appHop")
@Table(name="APP_HOP")
public class AppHop extends BaseEntity implements java.io.Serializable{
	
	/**
	 * 
	 */
	@ApiModelProperty(hidden=true)
	@JSONField(serialize = false)
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	private String hopId;
	@Excel(name = "医院名称", orderNum = "1")
	private String hopName;
	private String hopLevel;
	@ApiModelProperty(value="体检标志")
	private String hopTest;
	@ApiModelProperty(value="医院地址")
	private String hopAddress;
	@ApiModelProperty(value="医院图片")
	private String hopPic;
	@ApiModelProperty(hidden=true)
	@JSONField(serialize = false)
	private String hopStatus;
	@Transient
	@Excel(name = "医疗机构级别", orderNum = "2")
	@ApiModelProperty(hidden=true)
	@JSONField(serialize = false,deserialize=false)
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
	public String getHopTest() {
		return hopTest;
	}
	public void setHopTest(String hopTest) {
		this.hopTest = hopTest;
	}
	public String getHopAddress() {
		return hopAddress;
	}
	public void setHopAddress(String hopAddress) {
		this.hopAddress = hopAddress;
	}
	public String getHopPic() {
		return hopPic;
	}
	public void setHopPic(String hopPic) {
		this.hopPic = hopPic;
	}
	
	
	
}
