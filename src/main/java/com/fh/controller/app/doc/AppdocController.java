package com.fh.controller.app.doc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.system.Dictionaries;
import com.fh.entity.vo.Select;
import com.fh.entity.vo.doc.DocServiceVO;
import com.fh.service.common.impl.CommonService;
import com.fh.service.system.dictionaries.impl.DictionariesService;
import com.fh.service.system.doc.impl.DocService;
import com.fh.util.Constants;


@Controller 
@RequestMapping(value="/appdoc")
@Api(value = "医生列表", tags = "医生列表") 
public class AppdocController extends BaseController{

	
	@Autowired
	private DocService docService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private DictionariesService dictionariesService;
	
	/**
	 * 返回科室信息
	 * @return
	 */
	@ApiOperation(notes = "医生列表",  value = "医生列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "DOC_HOPID", value = "医院的id", required = false, dataType = "String"),
		@ApiImplicitParam(name = "DOC_LOCID", value = "科室id", required = false, dataType = "String"),
		@ApiImplicitParam(name = "DOC_TITLE", value = "医生职称", required = false, dataType = "String"),
		@ApiImplicitParam(name = "SHOW_COUNT", value = "一页的显示条数,传空默认为10", required = false, dataType = "String"),
		@ApiImplicitParam(name = "CURRENT_PAGE", value = "当前页数,不传默认为1", required = false, dataType = "String")
	})
	@RequestMapping(value="/getAppDoc",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<DocServiceVO> getAppDoc(){
		
		JsonResult<DocServiceVO> jsonResult=new JsonResult<DocServiceVO>();
		try {
			jsonResult.setDatas(docService.listPage(this.getAppPage()));
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(10);
			jsonResult.setMessage(e.getMessage());
		}
		return jsonResult;
	}
	
	/**
	 * 医生服务类型
	 * @return
	 */
	@ApiOperation(notes = "医生服务类型",  value = "医生服务类型")
	@RequestMapping(value="/getDocService",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<Select> getDocService(){
		
		JsonResult<Select> jsonResult=new JsonResult<Select>();
		List<Select> list=new ArrayList<Select>();
		jsonResult.setDatas(list);
		try {
			
			List<Dictionaries> dictionaries=dictionariesService.listSubDictByParentId(Constants.DIC_DOC_SERVICE);
			for(Dictionaries dictionaries2:dictionaries){
				list.add(new Select(dictionaries2.getDICTIONARIES_ID(), dictionaries2.getNAME()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(10);
			jsonResult.setMessage(e.getMessage());
		}
		return jsonResult;
	}
	
	
	/**
	 * 医生明细信息
	 * @return
	 */
	@ApiOperation(notes = "医生明细信息",  value = "医生明细信息")
	@RequestMapping(value="/getDocInfo",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<Select> getDocInfo(){
		
		JsonResult<Select> jsonResult=new JsonResult<Select>();
		List<Select> list=new ArrayList<Select>();
		jsonResult.setDatas(list);
		try {
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(10);
			jsonResult.setMessage(e.getMessage());
		}
		return jsonResult;
	}
}
