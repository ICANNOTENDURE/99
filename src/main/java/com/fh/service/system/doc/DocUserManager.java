package com.fh.service.system.doc;

import java.util.List;

import com.fh.dto.system.DocUserDto;
import com.fh.entity.Page;
import com.fh.entity.system.doc.DocInfo;


public interface DocUserManager {

	List<DocInfo> listPage(Page pg)throws Exception;
	
	void saveDoc(DocUserDto dto)throws Exception;
}
