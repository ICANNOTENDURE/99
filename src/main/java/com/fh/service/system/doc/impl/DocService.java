package com.fh.service.system.doc.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.dao.CrudSupport;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.system.doc.DocServiceReq;
import com.fh.entity.vo.doc.DocServiceVO;
import com.fh.service.system.doc.DocManager;
import com.fh.util.PageData;


@Service("docService")
public class DocService implements DocManager{
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Autowired
	private CrudSupport crudSupport;

	@SuppressWarnings("unchecked")
	@Override
	public List<DocServiceVO> listPage(Page pg) throws Exception {
		List<DocServiceVO> docServiceVOs=(List<DocServiceVO>) dao.findForList("DocServiceMapper.listPage", pg);
		return docServiceVOs;
	}

	@Override
	public void audit(PageData pd) throws Exception {
		com.fh.entity.system.doc.DocService docService=crudSupport.selectByPrimaryKey(com.fh.entity.system.doc.DocService.class, pd.getString("serviceId"));
		docService.setAuditFlag(pd.getString("status"));
		crudSupport.saveOrUpdate(docService);
		DocServiceReq docServiceReq=new DocServiceReq();
		docServiceReq.setContent(pd.getString("content"));
		docServiceReq.setStatus(pd.getString("status"));
		docServiceReq.setCreateDate(new Date());
		docServiceReq.setServiceId(docService.getServiceId());
		crudSupport.saveOrUpdate(docServiceReq);
	}
	

	
}
