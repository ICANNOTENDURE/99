package com.fh.service.system.doc;

import java.util.List;

import com.fh.entity.Page;
import com.fh.entity.system.doc.DocInfoReq;
import com.fh.util.PageData;


public interface DocAuthManager {

	List<DocInfoReq> listPage(Page pg)throws Exception;
	
	void auidt(PageData pd)throws Exception;

}
