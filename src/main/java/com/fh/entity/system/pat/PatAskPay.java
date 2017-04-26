package com.fh.entity.system.pat;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.fh.entity.BaseEntity;


@Table(name="pat_ask_pay")
public class PatAskPay extends BaseEntity{
	
	@Id
	private String payId;
	private String payTradeNo;
	private Date payDate;
	private BigDecimal payAmount;
	private String payType;
	private String payStatus;
	private String payAskId;
	public String getPayId() {
		return payId;
	}
	public void setPayId(String payId) {
		this.payId = payId;
	}
	public String getPayTradeNo() {
		return payTradeNo;
	}
	public void setPayTradeNo(String payTradeNo) {
		this.payTradeNo = payTradeNo;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getPayAskId() {
		return payAskId;
	}
	public void setPayAskId(String payAskId) {
		this.payAskId = payAskId;
	}
	
	
	
}