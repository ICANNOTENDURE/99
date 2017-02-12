package com.fh.service.system.doc.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.dao.CrudSupport;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.system.doc.DocPatGrpItm;
import com.fh.service.system.doc.PatGrpManager;


@Service("patGrpService")
public class PatGrpService implements PatGrpManager{
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Autowired
	private CrudSupport crudSupport;
	@SuppressWarnings("unchecked")
	@Override
	public List<DocPatGrpItm> listGrpItmPage(Page pg) throws Exception {
		
		return (List<DocPatGrpItm>) dao.findForList("PatGrpMapper.listPage", pg);
	}
	
	
	
}
