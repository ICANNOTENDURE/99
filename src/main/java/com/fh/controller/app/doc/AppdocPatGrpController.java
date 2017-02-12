package com.fh.controller.app.doc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.system.doc.DocPatGrp;
import com.fh.entity.system.doc.DocPatGrpItm;
import com.fh.plugin.annotation.AppToken;
import com.fh.service.common.impl.CommonService;
import com.fh.service.system.doc.impl.PatGrpService;
import com.fh.util.StringUtil;


@Controller 
@RequestMapping(value="/appdocpatgrp")
@Api(value = "病人分组", tags = "病人分组") 
public class AppdocPatGrpController extends BaseController{

	

	@Autowired
	private CommonService commonService;
	@Autowired
	private PatGrpService patGrpService;
	
	/**
	 * 新增或者修改病人分类
	 * @return
	 * @throws Exception 
	 */
	@AppToken
	@ApiOperation(notes = "新增或者修改病人分类",  value = "新增或者修改病人分类")
	@RequestMapping(value="/savePatGrp",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> savePatGrp(
			@ApiParam(value = "token",name="APP_TOKEN") @RequestParam String APP_TOKEN,
			@ApiParam(value = "grpName",name="分组名称") @RequestParam String grpName,
			@ApiParam(value = "grpId",name="分组id") @RequestParam String grpId) throws Exception{
		
		DocPatGrp docPatGrp=new DocPatGrp();
		docPatGrp.setGrpId(StringUtil.trim(grpId));
		docPatGrp.setGrpName(grpName);
		docPatGrp.setGrpDocId(this.getAppUserId());
		commonService.saveOrUpdate(docPatGrp);
		return new JsonResult<Object>();
	}
	/**
	 * 删除病人分类
	 * @return
	 * @throws Exception 
	 */
	@AppToken
	@ApiOperation(notes = "删除病人分类",  value = "删除病人分类")
	@RequestMapping(value="/deletePatGrp",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> deletePatGrp(
			@ApiParam(value = "token",name="APP_TOKEN") @RequestParam String APP_TOKEN,
			@ApiParam(value = "grpId",name="分组id") @RequestParam String grpId) throws Exception{
		

		commonService.deleteByPrimaryKey(DocPatGrp.class, grpId);
		return new JsonResult<Object>();
	}
	/**
	 * 查询病人分类
	 * @return
	 * @throws Exception 
	 */
	@AppToken
	@ApiOperation(notes = "查询病人分类",  value = "查询病人分类")
	@RequestMapping(value="/listPatGrp",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<DocPatGrp> listPatGrp(
			@ApiParam(value = "token",name="APP_TOKEN") @RequestParam String APP_TOKEN) throws Exception{
		JsonResult<DocPatGrp> jsonResult=new JsonResult<DocPatGrp>();
		Map<String, Object> conMapping=new HashMap<String, Object>();
		conMapping.put("grp_Doc_Id", this.getAppUserId());
		jsonResult.setDatas(commonService.selectByEqCon(DocPatGrp.class, conMapping));
		return jsonResult;
	}
	/**
	 * 给分类下增加病人
	 * @return
	 * @throws Exception 
	 */
	@AppToken
	@ApiOperation(notes = "给分类下增加病人",  value = "给分类下增加病人")
	@RequestMapping(value="/savePatGrpItm",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> savePatGrpItm(
			@ApiParam(value = "token",name="APP_TOKEN") @RequestParam String APP_TOKEN,
			@ApiParam(value = "grpItmId",name="grpItmId") @RequestParam String grpItmId,
			@ApiParam(value = "famId",name="病人家属id") @RequestParam String famId,
			@ApiParam(value = "grpId",name="分组id") @RequestParam String grpId) throws Exception{
		
		DocPatGrpItm docPatGrp=new DocPatGrpItm();
		docPatGrp.setGrpitmId(StringUtil.trim(grpItmId));
		docPatGrp.setGrpitmParent(grpId);
		docPatGrp.setGrpitmFamId(famId);
		commonService.saveOrUpdate(docPatGrp);
		return new JsonResult<Object>();
	}
	/**
	 * 删除分类下病人
	 * @return
	 * @throws Exception 
	 */
	@AppToken
	@ApiOperation(notes = "删除分类下病人",  value = "删除分类下病人")
	@RequestMapping(value="/deletePatGrpItm",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> deletePatGrpItm(
			@ApiParam(value = "token",name="APP_TOKEN") @RequestParam String APP_TOKEN,
			@ApiParam(value = "grpItmId",name="grpItmId") @RequestParam String grpItmId) throws Exception{

		commonService.deleteByPrimaryKey(DocPatGrpItm.class, grpItmId);
		return new JsonResult<Object>();
	}
	/**
	 * 查询分类下病人
	 * @return
	 * @throws Exception 
	 */
	@AppToken
	@ApiOperation(notes = "查询分类下病人",  value = "查询分类下病人")
	@RequestMapping(value="/listPatGrpItm",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<DocPatGrpItm> listPatGrpItm(
			@ApiParam(value = "token",name="APP_TOKEN") @RequestParam String APP_TOKEN,
			@ApiParam(value = "parent",name="分组id") @RequestParam String parent,
			@ApiParam(value = "一页的显示条数,传空默认为10",name="SHOW_COUNT") @RequestParam Long SHOW_COUNT,
			@ApiParam(value = "当前页数,不传默认为",name="CURRENT_PAGE") @RequestParam Long CURRENT_PAGE) throws Exception{
		JsonResult<DocPatGrpItm> jsonResult=new JsonResult<DocPatGrpItm>();
		jsonResult.setDatas(patGrpService.listGrpItmPage(this.getAppPage()));
		return jsonResult;
	}
}
