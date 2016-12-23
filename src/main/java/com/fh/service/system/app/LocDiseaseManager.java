package com.fh.service.system.app;

import java.util.List;

import com.fh.entity.Page;
import com.fh.entity.app.LocDisease;


public interface LocDiseaseManager {

	public List<LocDisease> listPage(Page page)throws Exception;
}
