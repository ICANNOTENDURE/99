package com.fh.service.system.app;

import java.util.List;

import com.fh.entity.app.AppLoc;


public interface LocManager {

	List<AppLoc> getByName(String name)throws Exception;
}
