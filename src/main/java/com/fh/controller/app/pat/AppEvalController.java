package com.fh.controller.app.pat;







import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.system.pat.AskEvalute;
import com.fh.entity.vo.pat.EvaluteVO;
import com.fh.plugin.annotation.AppToken;
import com.fh.service.common.impl.CommonService;
import com.fh.service.system.app.impl.EvalService;

@Controller
@RequestMapping(value="/appeval")
@Api(value = "病人评价", tags = "病人评价") 
public class AppEvalController extends BaseController{
	
	@Autowired
	private CommonService commonService;
	@Autowired
	private EvalService evalService;
	
	/**
	 * 评价订单
	 * @return
	 * @throws Exception 
	 */
	@AppToken
	@ApiOperation(notes = "评价订单",  value = "评价订单")
	@RequestMapping(value="/eval",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> eval(
			@ApiParam(value = "token",name="APP_TOKEN") @RequestParam String APP_TOKEN,
			@ApiParam(value = "askid",name="askid") @RequestParam String askid,
			@ApiParam(value = "医生态度",name="evalAttitude") @RequestParam Integer evalAttitude,
			@ApiParam(value = "治疗效果",name="evalEffect") @RequestParam Integer evalEffect,
			@ApiParam(value = "回答提问速度",name="evalReplySpeed") @RequestParam Integer evalReplySpeed,
			@ApiParam(value = "评论内容",name="evalContent") @RequestParam String evalContent
		) throws Exception{
		
		AskEvalute askEvalute=new AskEvalute();
		askEvalute.setParentId(askid);
		askEvalute.setEvalContent(evalContent);
		askEvalute.setEvalAttitude(evalAttitude);
		askEvalute.setEvalEffect(evalEffect);
		askEvalute.setEvalReplySpeed(evalReplySpeed);
		askEvalute.setPatId(this.getAppUserId());
		askEvalute.setCreateDate(new Date());
		evalService.saveEval(askEvalute);
		return new JsonResult<Object>();
	}
	
	@ApiOperation(notes = "显示医生评价",  value = "显示医生评价")
	@RequestMapping(value="/listEval",method = RequestMethod.GET)
	@ResponseBody
	public JsonResult<EvaluteVO> listEval(
			@ApiParam(value = "token",name="APP_TOKEN") @RequestParam String APP_TOKEN,
			@ApiParam(value = "医生id",name="docid") @RequestParam String docid,
			@ApiParam(value = "一页的显示条数,传空默认为10",name="SHOW_COUNT") @RequestParam Long SHOW_COUNT,
			@ApiParam(value = "当前页数,不传默认为",name="CURRENT_PAGE") @RequestParam Long CURRENT_PAGE
		
		) throws Exception{
		
		JsonResult<EvaluteVO> result=new JsonResult<EvaluteVO>();
		result.setDatas(evalService.listEval(this.getAppPage()));
		return result;
	}
}
