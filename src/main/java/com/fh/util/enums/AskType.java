package com.fh.util.enums;

public enum AskType {

	PAT("1"),
	DOC("2");

	private String type;
	
	private AskType(String type)
	{
		this.type = type;
	}
	
	public String getType()
	{
		return type;
	}
}
