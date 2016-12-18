package com.fh.entity.system;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.fh.entity.BaseEntity;


@Table(name="pat_user")
public class PatUser extends BaseEntity{
	
	@Id
	private String userId;
	private String userAccount;
	private String userPassword;
	private String status;
	private Date userBirth;
	private String userName;
	private String userMarry;
	private Date userLogindate;
	private String userSex;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(Date userBirth) {
		this.userBirth = userBirth;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserMarry() {
		return userMarry;
	}
	public void setUserMarry(String userMarry) {
		this.userMarry = userMarry;
	}
	public Date getUserLogindate() {
		return userLogindate;
	}
	public void setUserLogindate(Date userLogindate) {
		this.userLogindate = userLogindate;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	
	
	
	
	
	
}