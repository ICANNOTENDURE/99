package com.fh.entity.vo.token;

import java.util.Date;





public class Token {

	private String account;
	private String infoId;
	private Date expDate;
	//1:医生
	//2：病人
	private String accounttType;
	private Date logDate;
	
	
	
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	public Date getLogDate() {
		return logDate;
	}
	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}
	public String getAccounttType() {
		return accounttType;
	}
	public void setAccounttType(String accounttType) {
		this.accounttType = accounttType;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Date getExpDate() {
		return expDate;
	}
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	
	
}
