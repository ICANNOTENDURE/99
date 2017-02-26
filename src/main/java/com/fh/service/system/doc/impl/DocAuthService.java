package com.fh.service.system.doc.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.dao.CrudSupport;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.system.doc.DocInfo;
import com.fh.entity.system.doc.DocInfoReq;
import com.fh.service.system.doc.DocAuthManager;
import com.fh.util.PageData;


@Service("docAuthService")
public class DocAuthService implements DocAuthManager{
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@Autowired
	private CrudSupport crudSupport;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<DocInfoReq> listPage(Page pg) throws Exception {
		List<DocInfoReq> docServiceVOs=(List<DocInfoReq>) dao.findForList("DocAuthMapper.listPage", pg);
		return docServiceVOs;
	}

	@Override
	public void auidt(PageData pd) throws Exception {
		
		
		DocInfo docInfo=crudSupport.selectByPrimaryKey(DocInfo.class, pd.getString("infoId"));
		docInfo.setAuditFlag(pd.getString("status"));
		crudSupport.saveOrUpdate(docInfo);
		DocInfoReq docInfoReq=new DocInfoReq();
		docInfoReq.setContent(pd.getString("content"));
		docInfoReq.setCreateDate(new Date());
		docInfoReq.setStatus(pd.getString("status"));
		docInfoReq.setReqInfoId(docInfo.getInfoId());
		crudSupport.saveOrUpdate(docInfoReq);
	}
	

	
}
