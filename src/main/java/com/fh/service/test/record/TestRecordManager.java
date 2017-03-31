package com.fh.service.test.record;

import java.util.List;

import com.fh.entity.Page;
import com.fh.entity.test.AppTestRecord;



public interface TestRecordManager {
	

	
	List<AppTestRecord> list(Page pg)throws Exception;
	
}
