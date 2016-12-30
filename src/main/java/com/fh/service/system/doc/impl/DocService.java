package com.fh.service.system.doc.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.vo.doc.DocServiceVO;
import com.fh.service.system.doc.DocManager;


@Service("docService")
public class DocService implements DocManager{
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@SuppressWarnings("unchecked")
	@Override
	public List<DocServiceVO> listPage(Page pg) throws Exception {
		List<DocServiceVO> docServiceVOs=(List<DocServiceVO>) dao.findForList("DocServiceMapper.listPage", pg);
		return docServiceVOs;
	}
	

	
}
