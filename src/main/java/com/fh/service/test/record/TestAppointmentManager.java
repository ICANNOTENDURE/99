package com.fh.service.test.record;

import java.util.List;

import com.fh.entity.Page;
import com.fh.entity.vo.test.TestAppointVO;

public interface TestAppointmentManager {
	
	/**
	 * 保存预约记录
	 */
	void saveAppiont(String recordId,String patId)throws Exception;
	
	List<TestAppointVO> list(Page pg)throws Exception;
}
