package com.fh.service.system.app.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.vo.doc.DocInfoDetailVO;
import com.fh.service.system.app.StarDocManager;


@Service("starDocService")
public class StarDocService implements StarDocManager{
	

	
	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@SuppressWarnings("unchecked")
	@Override
	public List<DocInfoDetailVO> list(Page pg) throws Exception {
		// TODO Auto-generated method stub
		return (List<DocInfoDetailVO>) dao.findForList("StarDocMapper.listPage", pg);
	}
	
	
}
