package com.fh.controller.app.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.Page;
import com.fh.entity.test.AppTestRecord;
import com.fh.entity.test.AppTestResourse;
import com.fh.service.common.impl.CommonService;
import com.fh.service.test.record.impl.TestRecordService;
import com.fh.util.StringUtil;

/**
 * 体检预约 
 * @author ZDD
 *
 */
@Controller
@RequestMapping(value="/apptestcat")
@Api(value = "体检预约",tags = "体检预约")
public class AppTestCatController extends BaseController{

	@Autowired
	private CommonService commonService;
	@Autowired
	private TestRecordService testRecordService;
	
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
	
	@ApiOperation(value = "显示体检预约记录")
	@RequestMapping(value="/listTestRecord",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<AppTestRecord> listTestRecord(
			@ApiParam(name = "resourceId",value="体检资源id") @RequestParam String resourceId
			) throws Exception{
		JsonResult<AppTestRecord> jsonResult=new JsonResult<AppTestRecord>();
		Page page=new Page();
		page.setShowCount(30);
		page.setCurrentPage(1);
		
		Date start=new Date();
		Calendar cal=Calendar.getInstance();
		cal.setTime(start);	
		cal.add(Calendar.DATE, 1);
		page.getPd().put("startDate", start);
		cal.add(Calendar.DATE, 27);
		page.getPd().put("endDate", cal.getTime());
		page.getPd().put("resourceId",StringUtil.trim(resourceId));
		jsonResult.setDatas(testRecordService.list(page));
		return jsonResult;
	}
}
