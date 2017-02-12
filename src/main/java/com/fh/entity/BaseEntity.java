package com.fh.entity;

import io.swagger.annotations.ApiModelProperty;

public class BaseEntity {
	
	@ApiModelProperty(hidden=true)
	private String SYSNAME="DHCC";
	@ApiModelProperty(name="token")
	private String APP_TOKEN;
	
	
	
	public String getAPP_TOKEN() {
		return APP_TOKEN;
	}

	public void setAPP_TOKEN(String aPP_TOKEN) {
		APP_TOKEN = aPP_TOKEN;
	}

	public String getSYSNAME() {
		return SYSNAME;
	}

	public void setSYSNAME(String sYSNAME) {
		SYSNAME = sYSNAME;
	}
	
	
}
