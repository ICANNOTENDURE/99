package com.fh.util.enums;

public enum UserType {

	DOC("1"),
	PAT("2");
	
	private String type;
	
	private UserType(String type)
	{
		this.type = type;
	}
	
	public String getType()
	{
		return type;
	}
}
