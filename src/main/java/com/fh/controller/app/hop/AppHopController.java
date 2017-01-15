package com.fh.controller.app.hop;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.app.AppHop;
import com.fh.entity.system.Dictionaries;
import com.fh.entity.vo.Select;
import com.fh.service.common.impl.CommonService;
import com.fh.service.system.dictionaries.impl.DictionariesService;
import com.fh.util.Constants;


@Controller 
@RequestMapping(value="/apphop")
@Api(value = "医院信息", tags = "医院信息") 
public class AppHopController extends BaseController{

	
	@Autowired
	private CommonService commonService;
	@Autowired
	private DictionariesService dictionariesService;
	/**
	 * 医院级别
	 * @return
	 */
	@ApiOperation(notes = "医院级别",  value = "医院级别")
	@RequestMapping(value="/getAppHopLevel",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<Select> getAppHopLevel(){
		
		JsonResult<Select> jsonResult=new JsonResult<Select>();
		List<Select> list=new ArrayList<Select>();
		jsonResult.setDatas(list);
		try {

			List<Dictionaries> dictionaries=dictionariesService.listSubDictByParentId(Constants.DIC_HOP_LEVEL);
			for(Dictionaries dictionaries2:dictionaries){
				list.add(new Select(dictionaries2.getDICTIONARIES_ID(), dictionaries2.getNAME()));
			}
		} catch (Exception e) {
			jsonResult.setCode(11);
			jsonResult.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return jsonResult;
	}
	/**
	 * 医院级别
	 * @return
	 */
	@ApiOperation(notes = "根据医院级别获取 医院列表",  value = "医院级别")
	@RequestMapping(value="/getAppHop",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<AppHop> getAppHop(@ApiParam(value = "医院级别id",name="level", required = true) @RequestParam String level){
		JsonResult<AppHop> jsonResult=new JsonResult<AppHop>();
		try {
			Map<String, Object> conMapping=new HashMap<String, Object>();
			conMapping.put("hop_Level", "level");
			conMapping.put("hop_Status", "Y");
			List<AppHop> list=commonService.selectByEqCon(AppHop.class, conMapping);
			jsonResult.setDatas(list);
		} catch (Exception e) {
			jsonResult.setCode(11);
			jsonResult.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return jsonResult;
	}
}
