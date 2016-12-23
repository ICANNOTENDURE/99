package com.fh.service.system.app.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.app.LocDisease;
import com.fh.service.system.app.LocDiseaseManager;

@Service("locDiseaseService")
public class LocDiseaseService implements LocDiseaseManager{
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LocDisease> listPage(Page page) throws Exception {
		return (List<LocDisease>) dao.findForList("LocDiseaseMapper.listPage", page);
	}



}
