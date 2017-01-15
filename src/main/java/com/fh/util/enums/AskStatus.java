package com.fh.util.enums;

public enum AskStatus {

	CHAT_READY("1","待回复"),
	CHAT_ING("2","咨询中"),
	CHAT_END("3","咨询完成(待评价)"),
	RETURN("4","退费"),
	RET_REQUEST("5","退费申请");
	
	private String code;
	
	private String desc;
	
	private AskStatus(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}


	public String getCode() {
		return code;
	}


	public String getDesc() {
		return desc;
	}

	
	
}
