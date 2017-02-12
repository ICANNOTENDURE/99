package com.fh.service.system.doc;

import java.util.List;

import com.fh.entity.Page;
import com.fh.entity.system.doc.DocPatGrpItm;


public interface PatGrpManager {

	List<DocPatGrpItm> listGrpItmPage(Page pg)throws Exception;
}
