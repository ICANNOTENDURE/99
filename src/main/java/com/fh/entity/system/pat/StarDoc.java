package com.fh.entity.system.pat;

import javax.persistence.Id;
import javax.persistence.Table;

import com.fh.entity.BaseEntity;


@Table(name="PAT_STAR_DOC")
public class StarDoc extends BaseEntity{
	
	@Id
	private String starId;
	private String patId;
	private String docinfoId;
	public String getStarId() {
		return starId;
	}
	public void setStarId(String starId) {
		this.starId = starId;
	}
	public String getPatId() {
		return patId;
	}
	public void setPatId(String patId) {
		this.patId = patId;
	}
	public String getDocinfoId() {
		return docinfoId;
	}
	public void setDocinfoId(String docinfoId) {
		this.docinfoId = docinfoId;
	}
	
	
	
	
	
	
	
}