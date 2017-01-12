package com.fh.entity.vo.token;

import java.util.Date;





public class Token {

	private String account;
	private Date expDate;
	private String accounttType;
	private Date logDate;

	
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
