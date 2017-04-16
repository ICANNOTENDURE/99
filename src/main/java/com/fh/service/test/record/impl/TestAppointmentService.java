package com.fh.service.test.record.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.dao.CrudSupport;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.entity.test.AppTestAppionment;
import com.fh.entity.test.AppTestRecord;
import com.fh.entity.vo.test.TestAppointVO;
import com.fh.service.test.record.TestAppointmentManager;
import com.fh.util.enums.AppiontStatusEnum;


@Service("testAppointmentService")
public class TestAppointmentService implements TestAppointmentManager{
	

	@Autowired
	private CrudSupport crudSupport;
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@Override
	public void saveAppiont(String recordId, String patId) throws Exception {
		
		AppTestRecord appTestRecord=crudSupport.selectByPrimaryKey(AppTestRecord.class, recordId);
		Integer total=appTestRecord.getQty();
		Integer appiontQty=appTestRecord.getAppointentQty()==null?0:appTestRecord.getAppointentQty();
		if(appiontQty.intValue()>=total.intValue()){
			throw new RuntimeException("此号已预约完");
		}
		AppTestAppionment appionment=new AppTestAppionment();
		appionment.setDate(new Date());
		appionment.setPatId(patId);
		appionment.setRecordId(recordId);
		appionment.setStatus(AppiontStatusEnum.NORMAL.getCode());
		appionment.setPrice(appTestRecord.getPrice());
		appionment.setTestDate(appTestRecord.getDate());
		appionment.setResourseId(appTestRecord.getResourceId());
		
		appTestRecord.setAppointentQty(appiontQty.intValue()+1);
		appionment.setSeq(appiontQty.intValue()+1);
		crudSupport.saveOrUpdate(appTestRecord);
		crudSupport.saveOrUpdate(appionment);
	}

	@Override
	public List<TestAppointVO> list(Page pg) throws Exception {
		@SuppressWarnings("unchecked")
		List<TestAppointVO> findForList = (List<TestAppointVO>) dao.findForList("TestAppiontMapper.listPage", pg);
		return findForList;
	}


	
}
