package com.fh.entity.system;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;


@Table(name="pat_user")
public class PatUser {
	
	@Id
	private String USER_ID;
	private String USER_ACCOUNT;
	private String USER_PASSWORD;
	private String STATUS;
	private Date USER_BIRTH;
	private String USER_NAME;
	private String USER_MARRY;
	private Date USER_LOGINDATE;
	private String USER_SEX;
	
	
	
	
	public String getUSER_NAME() {
		return USER_NAME;
	}
	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}
	public String getUSER_MARRY() {
		return USER_MARRY;
	}
	public void setUSER_MARRY(String uSER_MARRY) {
		USER_MARRY = uSER_MARRY;
	}
	public Date getUSER_LOGINDATE() {
		return USER_LOGINDATE;
	}
	public void setUSER_LOGINDATE(Date uSER_LOGINDATE) {
		USER_LOGINDATE = uSER_LOGINDATE;
	}
	public String getUSER_SEX() {
		return USER_SEX;
	}
	public void setUSER_SEX(String uSER_SEX) {
		USER_SEX = uSER_SEX;
	}
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getUSER_ACCOUNT() {
		return USER_ACCOUNT;
	}
	public void setUSER_ACCOUNT(String uSER_ACCOUNT) {
		USER_ACCOUNT = uSER_ACCOUNT;
	}
	public String getUSER_PASSWORD() {
		return USER_PASSWORD;
	}
	public void setUSER_PASSWORD(String uSER_PASSWORD) {
		USER_PASSWORD = uSER_PASSWORD;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}
	public Date getUSER_BIRTH() {
		return USER_BIRTH;
	}
	public void setUSER_BIRTH(Date uSER_BIRTH) {
		USER_BIRTH = uSER_BIRTH;
	}
	
	
	
}