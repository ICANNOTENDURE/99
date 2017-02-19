package com.fh.service.system.app.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.dao.CrudSupport;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.system.pat.AskEvalute;
import com.fh.entity.system.pat.PatAsk;
import com.fh.entity.vo.pat.EvaluteVO;
import com.fh.service.system.app.EvalManager;
import com.fh.util.enums.AskStatus;


@Service("evalService")
public class EvalService implements EvalManager{
	
	@Autowired
	private CrudSupport crudSupport;
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@Override
	public void saveEval(AskEvalute askEvalute) throws Exception {
		
		PatAsk patAsk=crudSupport.selectByPrimaryKey(PatAsk.class, askEvalute.getParentId());
		if(AskStatus.CHAT_END.getCode().equals(patAsk.getAskStatus())){
			
			patAsk.setAskStatus(AskStatus.EVAL.getCode());
			crudSupport.saveOrUpdate(patAsk);
			askEvalute.setDocinfoId(patAsk.getAskDocid());
			crudSupport.saveOrUpdate(askEvalute);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EvaluteVO> listEval(Page pg) throws Exception {
		// TODO Auto-generated method stub
		return (List<EvaluteVO>) dao.findForList("EvalMapper.listPage", pg);
	}


	
}
