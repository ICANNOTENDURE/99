package com.fh.entity.vo;

public class Select {

	private String id;
	private String text;
	
	private String selected;
	
	public Select(String id, String text) {
		super();
		this.id = id;
		this.text = text;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
	

		
	
}
