package com.fh.entity.test;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name="APP_TEST_RESOURSE")
public class AppTestResourse implements Serializable{
	
	/**
	 * 
	 */@Transient
	private static final long serialVersionUID = -2714259307417714269L;
	@Id
	private String id;
	@ApiModelProperty(value = "体检名称")
	private String name;
	@ApiModelProperty(hidden=true)
	private String status;
	@ApiModelProperty(hidden=true)
	private String hopId;
	//号数
	@ApiModelProperty(value = "可约号数")
	private Integer qty;
	@ApiModelProperty(value = "价格")
	private BigDecimal price;
	@ApiModelProperty(value = "体检项目介绍")
	private String content;
	@Transient
	@ApiModelProperty(hidden=true)
	private String hopName;
	@Transient
	@ApiModelProperty(hidden=true)
	private String monday;
	@Transient
	@ApiModelProperty(hidden=true)
	private String tuesday;
	@Transient
	@ApiModelProperty(hidden=true)
	private String wednesday;
	@Transient
	@ApiModelProperty(hidden=true)
	private String thursday;
	@Transient
	@ApiModelProperty(hidden=true)
	private String friday;
	@Transient
	@ApiModelProperty(hidden=true)
	private String saturday ;
	@Transient
	@ApiModelProperty(hidden=true)
	private String sunday;
	@Transient
	@ApiModelProperty(hidden=true)
	private Integer mondayQty;
	@Transient
	@ApiModelProperty(hidden=true)
	private Integer tuesdayQty;
	@Transient
	@ApiModelProperty(hidden=true)
	private Integer wednesdayQty;
	@Transient
	@ApiModelProperty(hidden=true)
	private Integer thursdayQty;
	@Transient
	@ApiModelProperty(hidden=true)
	private Integer fridayQty;
	@Transient
	@ApiModelProperty(hidden=true)
	private Integer saturdayQty;
	@Transient
	@ApiModelProperty(hidden=true)
	private Integer sundayQty;
	
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getHopName() {
		return hopName;
	}
	public void setHopName(String hopName) {
		this.hopName = hopName;
	}
	public String getHopId() {
		return hopId;
	}
	public void setHopId(String hopId) {
		this.hopId = hopId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMonday() {
		return monday;
	}
	public void setMonday(String monday) {
		this.monday = monday;
	}
	public String getTuesday() {
		return tuesday;
	}
	public void setTuesday(String tuesday) {
		this.tuesday = tuesday;
	}
	public String getWednesday() {
		return wednesday;
	}
	public void setWednesday(String wednesday) {
		this.wednesday = wednesday;
	}
	public String getThursday() {
		return thursday;
	}
	public void setThursday(String thursday) {
		this.thursday = thursday;
	}
	public String getFriday() {
		return friday;
	}
	public void setFriday(String friday) {
		this.friday = friday;
	}
	public String getSaturday() {
		return saturday;
	}
	public void setSaturday(String saturday) {
		this.saturday = saturday;
	}
	public String getSunday() {
		return sunday;
	}
	public void setSunday(String sunday) {
		this.sunday = sunday;
	}
	public Integer getMondayQty() {
		return mondayQty;
	}
	public void setMondayQty(Integer mondayQty) {
		this.mondayQty = mondayQty;
	}
	public Integer getTuesdayQty() {
		return tuesdayQty;
	}
	public void setTuesdayQty(Integer tuesdayQty) {
		this.tuesdayQty = tuesdayQty;
	}
	public Integer getWednesdayQty() {
		return wednesdayQty;
	}
	public void setWednesdayQty(Integer wednesdayQty) {
		this.wednesdayQty = wednesdayQty;
	}
	public Integer getThursdayQty() {
		return thursdayQty;
	}
	public void setThursdayQty(Integer thursdayQty) {
		this.thursdayQty = thursdayQty;
	}
	public Integer getFridayQty() {
		return fridayQty;
	}
	public void setFridayQty(Integer fridayQty) {
		this.fridayQty = fridayQty;
	}
	public Integer getSaturdayQty() {
		return saturdayQty;
	}
	public void setSaturdayQty(Integer saturdayQty) {
		this.saturdayQty = saturdayQty;
	}
	public Integer getSundayQty() {
		return sundayQty;
	}
	public void setSundayQty(Integer sundayQty) {
		this.sundayQty = sundayQty;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
}
