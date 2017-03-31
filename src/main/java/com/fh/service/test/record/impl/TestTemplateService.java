package com.fh.service.test.record.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.vo.test.TestTemplateVO;
import com.fh.service.test.record.TestTemplateManager;


@Service("testTemplateService")
public class TestTemplateService implements TestTemplateManager{
	

	
	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@SuppressWarnings("unchecked")
	@Override
	public List<TestTemplateVO> list(Page pg) throws Exception {
		// TODO Auto-generated method stub
		return (List<TestTemplateVO>) dao.findForList("TestTemplateMapper.listPage", pg);
	}
	
	
}
