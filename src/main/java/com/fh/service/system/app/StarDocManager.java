package com.fh.service.system.app;

import java.util.List;

import com.fh.entity.Page;
import com.fh.entity.vo.doc.DocInfoDetailVO;



public interface StarDocManager {
	

	
	List<DocInfoDetailVO> list(Page pg)throws Exception;
	
}
