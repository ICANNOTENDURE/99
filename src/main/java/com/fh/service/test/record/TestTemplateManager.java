package com.fh.service.test.record;

import java.util.List;

import com.fh.entity.Page;
import com.fh.entity.vo.test.TestTemplateVO;



public interface TestTemplateManager {
	

	
	List<TestTemplateVO> list(Page pg)throws Exception;
	
}
