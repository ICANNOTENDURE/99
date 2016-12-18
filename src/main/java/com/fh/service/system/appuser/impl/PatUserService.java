package com.fh.service.system.appuser.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.service.system.appuser.PatUserManager;
import com.fh.util.PageData;



@Service("patuserService")
public class PatUserService  implements PatUserManager {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	


	@SuppressWarnings("unchecked")
	public List<PageData> listPdPageUser(Page page) throws Exception {
		return (List<PageData>) dao.findForList("PatUserMapper.userlistPage", page);
	}
	
	
	
}

