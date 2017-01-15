package com.fh.util.enums;

public enum MessageType {

	TEXT("1"),
	IMG("2"),
	VOICE("3");
	
	private String type;
	
	private MessageType(String type)
	{
		this.type = type;
	}
	
	public String getType()
	{
		return type;
	}
}
