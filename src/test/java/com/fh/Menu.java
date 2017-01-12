package com.fh;


public class Menu implements  Comparable<Menu>{
	
	private Integer id;
	
	private String name;
	
	private Integer patent;
	
	
	
	public Menu(Integer id, String name, Integer patent) {
		super();
		this.id = id;
		this.name = name;
		this.patent = patent;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPatent() {
		return patent;
	}

	public void setPatent(Integer patent) {
		this.patent = patent;
	}


	@Override
	public int compareTo(Menu o) {
		// TODO Auto-generated method stub
		return this.id-o.getId();
	}
	
	

}
