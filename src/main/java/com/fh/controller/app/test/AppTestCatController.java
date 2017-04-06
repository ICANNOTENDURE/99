package com.fh.controller.app.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.Page;
import com.fh.entity.test.AppTestResourse;
import com.fh.service.common.impl.CommonService;

/**
 * 体检预约 
 * @author ZDD
 *
 */
@Controller
@RequestMapping(value="/apptestcat")
@Api(value = "体检预约")
public class AppTestCatController extends BaseController{

	@Autowired
	private CommonService commonService;
	
	@ApiOperation(value = "显示体检分类")
	@RequestMapping(value="/listTestCat",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<AppTestResourse> listTestCat(
			@ApiParam(name = "hopId",value="医院id") @RequestParam String hopId,
			@ApiParam(name = "SHOW_COUNT",value="一页的显示条数,传空默认为10") @RequestParam Integer SHOW_COUNT,
			@ApiParam(name = "CURRENT_PAGE",value="当前页数,不传默认为1") @RequestParam Integer CURRENT_PAGE
			) throws Exception{
		JsonResult<AppTestResourse> jsonResult=new JsonResult<AppTestResourse>();
		Page page=this.getAppPage();
		page.getPd().put("status", "Y");
		jsonResult.setDatas(commonService.listPage(AppTestResourse.class, page));
		return jsonResult;
	}
}
