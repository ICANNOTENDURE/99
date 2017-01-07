package com.fh.controller.app.doc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fh.controller.base.BaseController;
import com.fh.entity.JsonResult;
import com.fh.entity.system.doc.DocUser;
import com.fh.entity.vo.token.Token;
import com.fh.service.common.impl.CommonService;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.MD5;
import com.fh.util.security.AESCoder;


@Controller 
@RequestMapping(value="/appdocuser")
@Api(value = "医生用户登录操作", tags = "医生用户登录操作") 
public class AppdocUserController extends BaseController{
	
	@Autowired
	private CommonService commonService;
	
	/**
	 * 医生用户手机验证码登陆
	 * @return
	 * @throws Exception 
	 */
	@ApiImplicitParams({
		@ApiImplicitParam(name = "account", value = "手机号", required = true, dataType = "String"),
		@ApiImplicitParam(name = "verifyCode", value = "验证码", required = true, dataType = "String")
	})
	@ApiOperation(notes = "短信验证码登陆",  value = "短信验证码登陆")
	@RequestMapping(value="/checkVerification",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> checkVerification(@RequestParam String account,@RequestParam String verifyCode) throws Exception{
		
		
		if(StringUtils.isBlank(account)||StringUtils.isBlank(verifyCode)){
			return new JsonResult<>(2, "账号或者验证码不能为空");
		}
		if(StringUtils.isBlank(getCookieValueByName(Const.COOKIE_Verification_Code))){
			return new JsonResult<>(3, "无效验证码,请重新获取验证码");
		}
		if(!verifyCode.equals(getCookieValueByName(Const.COOKIE_Verification_Code))){
			return new JsonResult<>(4, "验证码错误");
		}
		Map<String,Object> parMap=new HashMap<String, Object>();
		parMap.put("doc_Account", account);
		parMap.put("status", "Y");
		List<DocUser> docUsers = commonService.selectByEqCon(DocUser.class, parMap);
		if(docUsers.size()==0){
			return new JsonResult<>(5, "账号不存在");
		}
		docUsers.get(0).setDocLogindate(DateUtil.fomatTime(DateUtil.getTime()));
		commonService.saveOrUpdate(docUsers.get(0));
		Token token=new Token();
		token.setAccounttType("1");
		token.setAccount(docUsers.get(0).getDocAccount());
		token.setLogDate(docUsers.get(0).getDocLogindate());
		token.setExpDate(DateUtil.getExpDay(Const.APP_TOKEN_MAX_TIME));
		String tk=AESCoder.aesCbcEncrypt(JSON.toJSONString(token),Const.APP_TOKEN_KEY);
		return new JsonResult<Object>(0,JSON.toJSONString(tk));
	}
	
	/**
	 * 手机密码登陆
	 * @return
	 * @throws Exception 
	 */
	@ApiImplicitParams({
		@ApiImplicitParam(name = "account", value = "手机号", required = true, dataType = "String"),
		@ApiImplicitParam(name = "pwd", value = "密码", required = true, dataType = "String")
	})
	@ApiOperation(notes = "账号密码验证码登陆",  value = "账号密码验证码登陆")
	@RequestMapping(value="/checkByPwd",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult<Object> checkByPwd(@RequestParam String account,@RequestParam String pwd) throws Exception{
		
		
		if(StringUtils.isBlank(account)||StringUtils.isBlank(pwd)){
			return new JsonResult<>(2, "账号或者密码不能为空");
		}
		Map<String,Object> parMap=new HashMap<String, Object>();
		parMap.put("doc_Account", account);
		parMap.put("doc_Password",MD5.md5(pwd));
		parMap.put("status", "Y");
		List<DocUser> docUsers = commonService.selectByEqCon(DocUser.class, parMap);
		if(docUsers.size()==0){
			return new JsonResult<>(5, "账号不存在");
		}
		docUsers.get(0).setDocLogindate(DateUtil.fomatTime(DateUtil.getTime()));
		commonService.saveOrUpdate(docUsers.get(0));
		Token token=new Token();
		token.setAccounttType("1");
		token.setAccount(docUsers.get(0).getDocAccount());
		token.setLogDate(docUsers.get(0).getDocLogindate());
		token.setExpDate(DateUtil.getExpDay(Const.APP_TOKEN_MAX_TIME));
		String tk=AESCoder.aesCbcEncrypt(JSON.toJSONString(token),Const.APP_TOKEN_KEY);
		return new JsonResult<Object>(0,tk);
	}
}
