package com.fh.util.enums;

public enum ReadFlag {

	READ("1"),
	UNREAD("2");
	
	private String type;
	
	private ReadFlag(String type)
	{
		this.type = type;
	}
	
	public String getType()
	{
		return type;
	}
}
