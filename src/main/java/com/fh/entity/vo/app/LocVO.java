package com.fh.entity.vo.app;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

public class LocVO {
	
	@ApiModelProperty(value = "科室的id")
	private String id;
	@ApiModelProperty(value = "科室名称")
	private String name;
	@ApiModelProperty(value = "科室索引")
	private String key;
	@ApiModelProperty(value = "子科室")
	private List<LocVO> nodes;
	
	private String parent;
	
	
	
		
	public LocVO(String id, String name, String parent) {
		super();
		this.id = id;
		this.name = name;
		this.parent = parent;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<LocVO> getNodes() {
		if(nodes==null){
			nodes=new ArrayList<LocVO>();
		}
		return nodes;
	}
	public void setNodes(List<LocVO> nodes) {
		this.nodes = nodes;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	
	
}
