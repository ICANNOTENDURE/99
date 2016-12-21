package com.fh.service.system.app;

import java.util.List;

import com.fh.entity.Page;
import com.fh.entity.app.AppHop;

public interface HopManager {

	public List<AppHop> listPage(Page page)throws Exception;
}
