package com.fh.service.system.app.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.app.AppHop;
import com.fh.service.system.app.HopManager;


@Service("hopService")
public class HopService implements HopManager{
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@SuppressWarnings("unchecked")
	public List<AppHop> listPage(Page page) throws Exception {
		List<AppHop> appHops=(List<AppHop>) dao.findForList("HopMapper.listPage", page);
		return appHops;
	}
	
	
}
