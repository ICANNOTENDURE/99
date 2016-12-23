package com.fh.service.system.app.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.entity.app.AppLoc;
import com.fh.plugin.GeneralQueryParam;
import com.fh.service.common.impl.CommonService;
import com.fh.service.system.app.LocManager;


@Service("locService")
public class LocService implements LocManager{
	
	@Autowired
	private CommonService commonService;

	@Override
	public List<AppLoc> getByName(String name) throws Exception{
		String conditionExp = "loc_name = #{conditionParam.loc_name}";
		Map<String, Object> conditionParam = new HashMap<String, Object>();
		conditionParam.put("loc_name", name);
		GeneralQueryParam queryParam = new GeneralQueryParam(conditionExp,conditionParam);
		return commonService.selectAdvanced(AppLoc.class, queryParam);
	}
	
	
	
	
}
