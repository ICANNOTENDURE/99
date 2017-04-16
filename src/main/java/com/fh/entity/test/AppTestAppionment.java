package com.fh.entity.test;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fh.util.enums.AppiontStatusEnum;
/**
 * 预约记录表
 * @author ZDD
 *
 */
@Table(name="APP_TEST_APPIONTMENT")
public class AppTestAppionment implements Serializable{
	
	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = -2714259307417714269L;
	@Id
	private String id;
	/**
	 * 排班记录id(app_test_record)
	 */
	private String recordId;
	/**
	 * 资源id(app_test_resourse)
	 */
	private String resourseId;
	/**
	 * 体检日期
	 */
	private Date testDate;
	/**
	 * 预约时间
	 */
	private Date date;
	/**
	 * 病人id(pat_user)
	 */
	private String patId;
	/**
	 * 预约价格
	 */
	private BigDecimal price;
	/**
	 * 状态
	 * @see AppiontStatusEnum
	 */
	private String status;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getResourseId() {
		return resourseId;
	}
	public void setResourseId(String resourseId) {
		this.resourseId = resourseId;
	}
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPatId() {
		return patId;
	}
	public void setPatId(String patId) {
		this.patId = patId;
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
	
	
}
