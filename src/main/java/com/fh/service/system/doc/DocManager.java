package com.fh.service.system.doc;

import java.util.List;

import com.fh.entity.Page;
import com.fh.entity.vo.doc.DocServiceVO;
import com.fh.util.PageData;


public interface DocManager {

	List<DocServiceVO> listPage(Page pg)throws Exception;
	
	void audit(PageData pd)throws Exception;

}
