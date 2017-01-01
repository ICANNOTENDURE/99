package com.fh.controller.app.doc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.Page;
import com.fh.entity.vo.doc.DocServiceVO;
import com.fh.service.system.doc.impl.DocService;


@Controller 
@RequestMapping(value="/appdoc")
@Api(value = "医生列表", tags = "医生列表") 
public class AppdocController extends BaseController{

	
	@Autowired
	private DocService docService;
	
	/**
	 * 返回科室信息
	 * @return
	 */
	@ApiOperation(notes = "医生列表",  value = "医生列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "DOC_HOPID", value = "医院的id", required = false, dataType = "String"),
		@ApiImplicitParam(name = "DOC_LOCID", value = "科室id", required = false, dataType = "String"),
		@ApiImplicitParam(name = "DOC_TITLE", value = "医生职称", required = false, dataType = "String")
	})
	@RequestMapping(value="/getAppDoc",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<DocServiceVO> getAppDoc(){
		
		JsonResult<DocServiceVO> jsonResult=new JsonResult<DocServiceVO>();
		Page page=new Page();
		try {
			page.setPd(this.getPageData());
			jsonResult.setDatas(docService.listPage(page));
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult.setCode(10);
			jsonResult.setMessage(e.getMessage());
		}
		return jsonResult;
	}
}
