package com.fh.util.enums;

public enum DefaultImgEnum {
	
	DOC("empty.png"),
	PAT("empty.png");
	
	private String ImagePath;
	
	private DefaultImgEnum(String value)
	{
		this.ImagePath = value;
	}
	
	public String getImagePath()
	{
		return ImagePath;
	}
}
