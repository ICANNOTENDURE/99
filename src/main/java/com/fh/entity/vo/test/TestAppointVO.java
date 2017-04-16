package com.fh.entity.vo.test;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

import com.fh.util.enums.AppiontStatusEnum;



public class TestAppointVO {
	
	private String appointId;
	
	@ApiModelProperty(value = "体检医院")
	private String testHopName;
	@ApiModelProperty(value = "体检项目")
	private String testCatName;
	@ApiModelProperty(value = "体检日期")
	private Date testDate;
	@ApiModelProperty(value = "预约日期")
	private Date appointDate;
	@ApiModelProperty(value = "评价价格")
	private BigDecimal price;
	@ApiModelProperty(value = "评价序号")
	private Integer seq;
	@ApiModelProperty(value = "预约电话")
	private String patTel;
	@ApiModelProperty(value = "预约状态")
	private String status;
	
	public String getAppointId() {
		return appointId;
	}

	public void setAppointId(String appointId) {
		this.appointId = appointId;
	}

	public String getTestHopName() {
		return testHopName;
	}

	public void setTestHopName(String testHopName) {
		this.testHopName = testHopName;
	}

	public String getTestCatName() {
		return testCatName;
	}

	public void setTestCatName(String testCatName) {
		this.testCatName = testCatName;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public Date getAppointDate() {
		return appointDate;
	}

	public void setAppointDate(Date appointDate) {
		this.appointDate = appointDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getPatTel() {
		return patTel;
	}

	public void setPatTel(String patTel) {
		this.patTel = patTel;
	}

	public String getStatus() {
		return AppiontStatusEnum.getValueByKey(status);
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	
}
