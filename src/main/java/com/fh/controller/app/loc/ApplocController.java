package com.fh.controller.app.loc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.app.AppLoc;
import com.fh.entity.vo.app.LocVO;
import com.fh.service.common.impl.CommonService;


@Controller 
@RequestMapping(value="/apploc")
@Api(value = "科室信息", tags = "科室信息") 
public class ApplocController extends BaseController{

	
	@Autowired
	private CommonService commonService;
	
	/**
	 * 返回科室信息
	 * @return
	 */
	@ApiOperation(notes = "查询科室信息",  value = "查询科室信息")
	@RequestMapping(value="/getAppLoc",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<LocVO> getAppLoc(){
		
		JsonResult<LocVO> jsonResult=new JsonResult<LocVO>();
		List<LocVO> list=new ArrayList<LocVO>();
		jsonResult.setDatas(list);
		try {
			Map<String, Object> conMapping=new HashMap<String, Object>();
			Map<String, LocVO> locMap = new LinkedHashMap<String, LocVO>();
			conMapping.put("loc_Status", "Y");
			List<AppLoc> appLocs=commonService.selectByEqCon(AppLoc.class, conMapping); 
			for(AppLoc loc:appLocs){
				LocVO locVO=new LocVO(loc.getLocId(),loc.getLocName(),loc.getLocParent());
				if(loc.getLocParent().equals("0")){
					list.add(locVO);
				}
				locMap.put(loc.getLocId(), locVO);
			}
			//遍历Map缕清父子关系
			Iterator<String> keys = locMap.keySet().iterator();
			while(keys.hasNext()){			
				String key = keys.next();		
				LocVO loc = locMap.get(key);	
				if(!"0".equals(loc.getParent())){
					LocVO locVO = locMap.get(loc.getParent());
					locVO.getNodes().add(loc);
				}
			}
			
		} catch (Exception e) {
			jsonResult.setCode(11);
			jsonResult.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return jsonResult;
	}
}
