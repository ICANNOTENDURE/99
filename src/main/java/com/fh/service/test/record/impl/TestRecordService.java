package com.fh.service.test.record.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.test.AppTestRecord;
import com.fh.service.test.record.TestRecordManager;


@Service("testRecordService")
public class TestRecordService implements TestRecordManager{
	

	
	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@SuppressWarnings("unchecked")
	@Override
	public List<AppTestRecord> list(Page pg) throws Exception {
		// TODO Auto-generated method stub
		return (List<AppTestRecord>) dao.findForList("TestRecordMapper.listPage", pg);
	}
	
	
}
