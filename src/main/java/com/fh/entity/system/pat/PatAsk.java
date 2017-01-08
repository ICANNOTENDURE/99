package com.fh.entity.system.pat;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.fh.entity.BaseEntity;


@Table(name="PAT_ASK")
public class PatAsk extends BaseEntity{
	
	@Id
	private String askId;
	private String askContent;
	private Date askPatid;
	private String askFamid;
	private String askDocid;
	private Date askDate;
	private String askEvalFlag;
	
	
}