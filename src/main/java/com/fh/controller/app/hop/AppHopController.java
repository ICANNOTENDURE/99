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
import com.fh.entity.Page;
import com.fh.entity.app.AppHop;
import com.fh.entity.system.Dictionaries;
import com.fh.entity.vo.Select;
import com.fh.service.common.impl.CommonService;
import com.fh.service.system.app.impl.HopService;
import com.fh.service.system.dictionaries.impl.DictionariesService;
import com.fh.util.Constants;
import com.fh.util.StringUtil;


@Controller 
@RequestMapping(value="/apphop")
@Api(value = "医院信息", tags = "医院信息") 
public class AppHopController extends BaseController{

	
	@Autowired
	private CommonService commonService;
	@Autowired
	private DictionariesService dictionariesService;
	@Autowired
	private HopService hopService;
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
	 * 医院
	 * @return
	 */
	@ApiOperation(value = "根据医院级别获取 医院列表")
	@RequestMapping(value="/getAppHop",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<AppHop> getAppHop(
			@ApiParam(value = "医院级别id",name="level") @RequestParam String level){
		JsonResult<AppHop> jsonResult=new JsonResult<AppHop>();
		try {
			Map<String, Object> conMapping=new HashMap<String, Object>();
			if(StringUtil.trim(level)!=null){
				conMapping.put("hop_Level", level);
			}
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
	
	/**
	 * 医院
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value = "查询体检医院，分页查询医院")
	@RequestMapping(value="/getAppHopPage",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<AppHop> getAppHopPage(
			@ApiParam(value = "关键字",name="key") @RequestParam String key,
			@ApiParam(value = "是否体检",name="test") @RequestParam String test,
			@ApiParam(value="一页的显示条数,传空默认为10",name = "SHOW_COUNT") @RequestParam String SHOW_COUNT,
			@ApiParam(value="当前页数,不传默认为1",name = "CURRENT_PAGE") @RequestParam String CURRENT_PAGE) throws Exception{
		JsonResult<AppHop> jsonResult=new JsonResult<AppHop>();
		Page page=getAppPage();
		page.getPd().put("HOP_STATUS", 'Y');
		page.getPd().put("keywords", StringUtil.trim(key));
		page.getPd().put("test", StringUtil.trim(test));	
		jsonResult.setDatas(hopService.listPage(page));
		return jsonResult;
	}
}
